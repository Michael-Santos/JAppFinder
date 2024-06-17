package com.example.jappfinder.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.BrowserDriverImpl;
import com.example.jappfinder.driver.PropertyAdditionalInfo;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;
import com.microsoft.playwright.BrowserContext;

@Service
public class ScrapperImpl implements Scrapper {
	
	private BrowserDriver driver;
	
	private BrowserDriver getDriver() {
		if (driver != null) 
			return driver;
	
		driver = new BrowserDriverImpl();
		return driver;
	}
	
	@Override
	public List<PropertyInfo> getProperties(SearchFilter filter) {
		return getDriver().fetchProperties(filter);
	}

	@Override
	public PropertyAdditionalInfo getAdditionalInfo(String url) {
		return getDriver().fetchPropertyAdditionalInfo(url);
	}
	
}
