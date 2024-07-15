package com.example.jappfinder.services;

import com.example.jappfinder.config.ScrapperProperties;
import com.example.jappfinder.driver.BrowserDriver;

public interface BrowserDriverFactory {
	BrowserDriver create(boolean useProxy, ScrapperProperties scrapperProperties);
}
