package com.example.jappfinder.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.jappfinder.driver.PropertyInfo;
import com.example.jappfinder.repositories.models.Property;
import com.example.jappfinder.repositories.models.Query;
import com.example.jappfinder.repositories.models.Status;
import com.example.jappfinder.services.QueryService;
import com.example.jappfinder.services.ScrapperFactory;

@Component
public class FetchPropertyInfoJob {
	
	private static final Logger logger = LoggerFactory.getLogger(FetchPropertyInfoJob.class);
	private final ScrapperFactory scrapperFactory;
	private final QueryService queryService;
	
	public FetchPropertyInfoJob(ScrapperFactory scrapperFactory, QueryService queryService) {
		this.scrapperFactory = scrapperFactory;
		this.queryService = queryService;
	}
	
	@Scheduled(fixedDelay = 5000)
    public void performTask() {
		  
		  var jobOptional = queryService.getFirstNotStarted();
		  if (jobOptional.isEmpty()) 
			  return;
		  
		  var job = jobOptional.get();
		  
		  logger.info("[FetchPropertyInfoJob] A job was found: ID {}", job.getId());
		  
		  var scrapper = scrapperFactory.create();
		  var filter = job.mapToSearchFilter();
		  var properties = scrapper.getProperties(filter);
		  var propertiesMapped = properties.stream().map((p) -> MapProperty(p, job.getId())).toList();
		  scrapper.close();
		  
		  logger.info("[FetchPropertyInfoJob] It was found {} properties for job id {}", propertiesMapped.size(), job.getId());
		  
		  job.setProperties(propertiesMapped);
		  job.setStatus(Status.PROPERTIES_FECTHED);
		  queryService.update(job);
		  
		  logger.info("[FetchPropertyInfoJob] Job id {} was updated!", job.getId());
		  
    }
	
	private Property MapProperty(PropertyInfo propertyToMap, Long queryId) {
		var property = new Property();
		property.setAddress(propertyToMap.getAddress());
		property.setBathrooms(propertyToMap.getBathrooms());
		property.setBedrooms(propertyToMap.getBedrooms());
		property.setDimension(propertyToMap.getDimension());
		property.setGarage(propertyToMap.getGarage());
		property.setPrice(propertyToMap.getPrice());
		property.setUrl(propertyToMap.getUrl());
		property.setQuery(new Query(queryId));
		return property;
	}
	
}
