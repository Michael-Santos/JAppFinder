package com.example.jappfinder.services;

import com.example.jappfinder.driver.PropertyAdditionalInfo;
import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.driver.SearchFilter;

import java.util.List;

public interface Scrapper {
	List<PropertyInfo> getProperties(SearchFilter filter); 
	PropertyAdditionalInfo getAdditionalInfo(String url);
}
