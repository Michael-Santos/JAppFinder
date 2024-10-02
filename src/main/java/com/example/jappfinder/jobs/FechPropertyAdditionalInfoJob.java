package com.example.jappfinder.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.jappfinder.driver.PropertyAdditionalInfo;
import com.example.jappfinder.repositories.models.Property;
import com.example.jappfinder.repositories.models.Status;
import com.example.jappfinder.services.PropertyService;
import com.example.jappfinder.services.QueryService;
import com.example.jappfinder.services.Scrapper;
import com.example.jappfinder.services.ScrapperFactory;

@Component
public class FechPropertyAdditionalInfoJob {

	private static final Logger logger = LoggerFactory.getLogger(FetchPropertyInfoJob.class);
	private final ScrapperFactory scrapperFactory;
	private final QueryService queryService;
	private final PropertyService propertyService;
	
	public FechPropertyAdditionalInfoJob(ScrapperFactory scrapperFactory, QueryService queryService, PropertyService propertyService) {
		this.scrapperFactory = scrapperFactory;
		this.queryService = queryService;
		this.propertyService = propertyService;
	}
	
	@Scheduled(fixedDelay = 5000)
	public void performTask() throws InterruptedException {
		
		var jobOptional = queryService.getFirstPropertiesFetched();
			if (jobOptional.isEmpty()) 
				return;
		  
		var job = jobOptional.get();
		
		logger.info("[FetchPropertyAdditionalInfoJob] A job was found: ID {}", job.getId());
		
		var propertiesTotal = propertyService.countByQueryId(job.getId());
		
		logger.info("[FetchPropertyAdditionalInfoJob] The job ID {} has {} properties to get additional info", job.getId(), propertiesTotal);
	
		var pageSize = 15;
		var totalPages = propertiesTotal / pageSize;
		if (propertiesTotal % pageSize > 0) totalPages++;
		
		for(int i=0; i < totalPages; i++) {
			logger.info("[FetchPropertyAdditionalInfoJob] Starting to fetch properties additional info");			
			
			var properties = propertyService.getByQueryId(job.getId(), i, pageSize);			
			for(int j=0; j < properties.size(); j++) {				
				var additionalInfo = fetchAdditionalInfoWithRetry(properties.get(j).getUrl(), properties.get(j).getId());
				Thread.sleep(3000);
				var propety = fillUpAdditionalInfo(additionalInfo, properties.get(j));
				propertyService.update(propety);
			}
						
			logger.info("[FetchPropertyAdditionalInfoJob] Waiting 30s to fetch more properties additional info");
			Thread.sleep(30000);
		}
		
		job.setStatus(Status.COMPLETED);
		queryService.update(job);
		logger.info("[FetchPropertyAdditionalInfoJob] Job {} completed!", job.getId());
	}
	
	private PropertyAdditionalInfo fetchAdditionalInfoWithRetry(String url, Long properyId) throws InterruptedException {
		var maxRetries = 3;
		Scrapper scrapper = null;
		do {
			try {
				scrapper = scrapperFactory.create(false);
				var result = scrapper.getAdditionalInfo(url);
				scrapper.close();
				return result;
			} catch (Exception ex) {
				maxRetries--;
			}
			
			scrapper.close();
			logger.warn("[FetchPropertyAdditionalInfoJob] It was not possible to fetch the additional info for propertyId {}. Retry in 15s", properyId);
			Thread.sleep(15000);
		} while (maxRetries > 0);	

		logger.error("[FetchPropertyAdditionalInfoJob] It was not possible to fetch the additional info for propertyId {}.", properyId);
		
		return new PropertyAdditionalInfo();
	}
	
	private Property fillUpAdditionalInfo(PropertyAdditionalInfo propertyAdditionalInfo, Property property) {
		property.setLatitude(propertyAdditionalInfo.getLatitude());
		property.setLongitude(propertyAdditionalInfo.getLongitude());
		property.setPublisher(propertyAdditionalInfo.getPublisher());
		return property;
	}
	
}
