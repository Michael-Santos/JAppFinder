package com.example.jappfinder.services;

import org.springframework.stereotype.Service;

import com.example.jappfinder.config.ScrapperProperties;

@Service
public class ScrapperFactoryImpl implements ScrapperFactory {

	private final BrowserDriverFactory browserFactory;
	private final ScrapperProperties scrapperProperties;
	
	public ScrapperFactoryImpl(BrowserDriverFactory browserFactory, ScrapperProperties scrapperProperties) {
		this.browserFactory = browserFactory;
		this.scrapperProperties = scrapperProperties;
	}
	
	@Override
	public Scrapper create(boolean useProxy) {
		return new ScrapperImpl(browserFactory, useProxy, scrapperProperties);
	}
	
}
