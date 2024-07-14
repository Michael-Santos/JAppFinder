package com.example.jappfinder.driver;

import java.nio.file.FileSystems;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;

public class BrowserDriverImpl implements BrowserDriver {

	private BrowserContext browser;

	private Page page;

	public BrowserDriverImpl() {
	}

	private BrowserContext Initilize() {
		var userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 OPR/109.0.0.0";

		var browserOptions = new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized"));
		var browserNewContext = new Browser.NewContextOptions().setUserAgent(userAgent).setViewportSize(null);

		Playwright playwright = Playwright.create();
		var browser = playwright.chromium().launch(browserOptions);
		return browser.newContext(browserNewContext);
	}

	private BrowserContext getBrowser() {
		if (browser != null)
			return browser;

		browser = Initilize();
		return browser;
	}

	private Page getPage() {
		if (page != null)
			return page;

		page = getBrowser().newPage();
		return page;
	}

	@Override
	public List<PropertyInfo> fetchProperties(SearchFilter filter) {
		var properties = new ArrayList<PropertyInfo>();
		var page = getBrowser().newPage();
		var url = String.format("https://www.vivareal.com.br/%s/%s/%s", filter.getOperationType().getValue(),
				normalizeState(filter.getState()), normalizeCity(filter.getCity()));
		page.navigate(url);

		var numberPropertiesTotal = getTotalNumberProperties(page);
		var pageSize = getPageSize(page);
		var totalPages = (numberPropertiesTotal / pageSize);
		if (numberPropertiesTotal % pageSize > 0)
			totalPages++;

		var pagesToFetch = filter.getMaxPages() < totalPages ? filter.getMaxPages() : totalPages;

		for (var pageNumber = 1; pageNumber <= pagesToFetch; pageNumber++) {
			if (pageNumber != 1)
				goToPage(pageNumber, page, filter);
			var propertiesPageResult = fetchPropertiesOnPage(page);
			properties.addAll(propertiesPageResult);
		}
		return properties;
	}

	private void goToPage(int pageNumber, Page page, SearchFilter filter) {
		var url = String.format("https://www.vivareal.com.br/%s/%s/%s?pagina=%s", filter.getOperationType(),
				normalizeState(filter.getState()), normalizeCity(filter.getCity()), pageNumber);
		page.navigate(url);
		page.screenshot(new ScreenshotOptions().setPath(FileSystems.getDefault().getPath("screenshot.png")));
	}

	private List<PropertyInfo> fetchPropertiesOnPage(Page page) {
		var properties = new ArrayList<PropertyInfo>();
		var resultList = page.locator("[data-type=\"property\"]");
		var propertiesSections = resultList.all();
		for (var propertiesSection : propertiesSections) {
			var property = fetchPropertyInfos(propertiesSection);
			properties.add(property);
		}

		return properties;
	}

	private PropertyInfo fetchPropertyInfos(Locator locator) {
		var property = new PropertyInfo();
		property.setUrl(getUrl(locator));
		property.setDimension(getDimension(locator));
		property.setBathrooms(getBathrooms(locator));
		property.setBedrooms(getBedrooms(locator));
		property.setGarage(getGarage(locator));
		property.setPrice(getPrice(locator));
		property.setAddress(getAddress(locator));
		return property;
	}

	private String getAddress(Locator locator) {
		var addressSection = locator.locator(".property-card__address").first();
		var text = addressSection.textContent();
		return text.trim();
	}

	private String getPrice(Locator locator) {
		var valueCardSection = locator.locator(".property-card__values").first();
		var priceSection = valueCardSection.locator(".property-card__price").first();
		var text = priceSection.textContent();
		return text.replace("Preço abaixo do mercado", "").replace("A partir de", "").trim();
	}

	private String getGarage(Locator locator) {
		var cardSection = locator.locator("li.property-card__detail-garage").first();
		var garageSection = cardSection.locator("span.js-property-card-value").first();
		var text = garageSection.textContent();
		return text.trim();
	}

	private String getBedrooms(Locator locator) {
		var cardSection = locator.locator("li.property-card__detail-room").first();
		var bedroomSection = cardSection.locator("span.js-property-card-value").first();
		var text = bedroomSection.textContent();
		return text.trim();
	}

	private String getBathrooms(Locator locator) {
		var cardSection = locator.locator("li.property-card__detail-bathroom").first();
		var bathroomSection = cardSection.locator("span.js-property-card-value").first();
		var text = bathroomSection.textContent();
		return text.trim();
	}

	private String getDimension(Locator locator) {
		var dimentionSection = locator.locator("span.js-property-card-detail-area").first();
		var text = dimentionSection.textContent();
		return text.trim();
	}

	private String getUrl(Locator locator) {
		var baseUrl = "https://www.vivareal.com.br";
		var link = locator.locator("a").first().getAttribute("href");
		return baseUrl + link;
	}

	private int getPageSize(Page page) {
		var resultList = page.locator("[data-type=\"property\"]");
		var result = resultList.all();
		return result.size();
	}

	private int getTotalNumberProperties(Page page) {
		var text = page.locator(".js-total-records");
		var value = text.textContent().replace(".", "");

		try {
			return Integer.parseInt(value.trim());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	// TODO: Move this method to another class
	@Override
	public PropertyAdditionalInfo fetchPropertyAdditionalInfo(String url) {
		var page = getPage();
		page.navigate(url);
		wait(2);
		
		var propertyAdditionalInfo = new PropertyAdditionalInfo();
		var coordinates = getCoordinates(page);
		propertyAdditionalInfo.setLatitude(coordinates.getLatitude());
		propertyAdditionalInfo.setLongitude(coordinates.getLongitude());
		
		closeMapIfNecessary();
		
		propertyAdditionalInfo.setPublisher(getPublisher(page));
		return propertyAdditionalInfo;
	}
	
	private Locator findMapButton() {
		var mapButton = page.locator("#modal-map-button");
		return mapButton.count() > 0 ? mapButton.first() : null;
	}
	
	private void closeMapIfNecessary() {
		var buttton = page.locator("//button[@aria-label='Fechar']");
		if (buttton.count() > 0) buttton.click();
	}

	private Coordinates getCoordinates(Page page) {
		var coordinates = new Coordinates();
		
		page.waitForRequest((request) -> {
			if (request.url().contains("maps/embed")) {
				var locationQueryString = request.url().split("&q=")[1];
                var coordidatesSplited = locationQueryString.split(",");
                
                try {
                	Float.parseFloat(coordidatesSplited[0].trim());
                } catch (NumberFormatException nfe) {
                	coordinates.setLatitude(null);
                    coordinates.setLongitude(null);
                    return true;
                }
                
                coordinates.setLatitude(coordidatesSplited[0]);
                coordinates.setLongitude(coordidatesSplited[1]);
				return true;
			}
			return false;
		}, () -> {
			var mapButton = findMapButton();
			if (mapButton != null) {
				mapButton.click();
			} else {
				page.locator(".map__navigate").click();
			}
		});
		
		return coordinates;
	}

	private String getPublisher(Page page) {
		var publisherSection = page.locator(".publisher__name");
		
		if (publisherSection.count() == 0) 
			publisherSection = page.locator(".advertiser-info__credentials");
		
		var text = publisherSection.first().textContent();
		return text.trim();
	}

	@Override
	public void close() {
		browser.close();
	}
	
	private static String normalizeCity(String value) {
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        var valueWithoutAccents = pattern.matcher(normalizer).replaceAll("");
        return valueWithoutAccents.replace(" ", "-").toLowerCase();
	}
	
	private static String normalizeState(String value) {
		return value.toLowerCase();
	}
	
	private static void wait(int seconds) {
		try {
		    Thread.sleep(seconds * 1000);
		} catch (InterruptedException ie) {
		    Thread.currentThread().interrupt();
		}
	}
	
}
