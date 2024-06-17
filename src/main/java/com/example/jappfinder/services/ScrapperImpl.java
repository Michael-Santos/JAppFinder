package com.example.jappfinder.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.PropertyAdditionalInfo;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;

@Service
public class ScrapperImpl implements Scrapper {

	private final BrowserDriver driver;	
	
	public ScrapperImpl(BrowserDriverFactory browserFactory) {
		this.driver = browserFactory.create();
	}
	
	@Override
	public List<PropertyInfo> getProperties(SearchFilter filter) {
		return driver.fetchProperties(filter);
	}

	@Override
	public PropertyAdditionalInfo getAdditionalInfo(String url) {
		return driver.fetchPropertyAdditionalInfo(url);
	}
	
}
