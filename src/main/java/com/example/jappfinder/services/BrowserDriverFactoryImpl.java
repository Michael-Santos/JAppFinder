package com.example.jappfinder.services;

import org.springframework.stereotype.Service;

import com.example.jappfinder.config.ScrapperProperties;
import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.BrowserDriverImpl;

@Service
public class BrowserDriverFactoryImpl implements BrowserDriverFactory {

	@Override
	public BrowserDriver create(boolean useProxy, ScrapperProperties scrapperProperties) {
		return new BrowserDriverImpl(useProxy, scrapperProperties);
	}

}
