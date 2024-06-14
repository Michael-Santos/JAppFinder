package com.example.jappfinder.driver;

import java.util.List;

public interface BrowserDriver {
	List<PropertyInfo> fetchProperties(SearchFilter filter);
	List<String> searchCitiesAvailable();
}
