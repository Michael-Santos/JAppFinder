package com.example.jappfinder.services;

public interface ScrapperFactory {
	Scrapper create(boolean useProxy);
}
