package com.example.jappfinder.services;

import org.springframework.stereotype.Service;

@Service
public class ScrapperFactoryImpl implements ScrapperFactory {

	private final BrowserDriverFactory browserFactory;
	
	public ScrapperFactoryImpl(BrowserDriverFactory browserFactory) {
		this.browserFactory = browserFactory;	
	}
	
	@Override
	public Scrapper create() {
		return new ScrapperImpl(browserFactory);
	}
	
}
