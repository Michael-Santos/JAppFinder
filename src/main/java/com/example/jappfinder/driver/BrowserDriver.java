package com.example.jappfinder.driver;

import java.util.List;

public interface BrowserDriver {
	List<PropertyInfo> fetchProperties(SearchFilter filter);
	PropertyAdditionalInfo fetchPropertyAdditionalInfo(String url);
	List<String> searchCitiesAvailable();
}
