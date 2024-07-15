package com.example.jappfinder.services;

import java.util.List;

import com.example.jappfinder.config.ScrapperProperties;
import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.PropertyAdditionalInfo;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;

public class ScrapperImpl implements Scrapper {

	private final BrowserDriver driver;
	
	public ScrapperImpl(BrowserDriverFactory browserFactory, boolean useProxy, ScrapperProperties scrapperProperties) {
		this.driver = browserFactory.create(useProxy, scrapperProperties);
	}
	
	@Override
	public List<PropertyInfo> getProperties(SearchFilter filter) {
		return driver.fetchProperties(filter);
	}

	@Override
	public PropertyAdditionalInfo getAdditionalInfo(String url) {
		return driver.fetchPropertyAdditionalInfo(url);
	}

	@Override
	public void close() {
		driver.close();
	}	
	
}
