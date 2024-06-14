package com.example.jappfinder.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.BrowserDriverImpl;
import com.example.jappfinder.driver.PropertyInfo;
import com.microsoft.playwright.BrowserContext;

@Service
public class ScrapperImpl implements Scrapper {
	
	private BrowserDriver driver;
	
	@Override
	public List<PropertyInfo> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private BrowserDriver getDriver() {
		if (driver != null) 
			return driver;
	
		driver = new BrowserDriverImpl();
		return driver;
	}
	
}
