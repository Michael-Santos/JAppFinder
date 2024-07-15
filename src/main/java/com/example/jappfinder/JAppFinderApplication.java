package com.example.jappfinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.jappfinder.config.ScrapperProperties;
import com.example.jappfinder.driver.BrowserDriverImpl;
import com.example.jappfinder.driver.SearchFilter;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan
@EnableScheduling
@EnableConfigurationProperties(ScrapperProperties.class)
public class JAppFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JAppFinderApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			
			/* var browser = new BrowserDriverImpl(); */
			/*
			 * var result = browser.fetchPropertyAdditionalInfo(
			 * "https://www.vivareal.com.br/imovel/casa-3-quartos-vila-haro-bairros-sorocaba-com-garagem-60m2-venda-RS350000-id-2698562133/"
			 * );
			 */
			
			/*
			 * var filter = new SearchFilter("sp", "sorocaba"); filter.setMaxDimension(80);
			 * filter.setMinDimension(50); filter.setMaxPrice(300000);
			 * filter.setMinPrice(100000);
			 * 
			 * 
			 * var results = browser.fetchProperties(filter);
			 * System.out.println(results.size());
			 */
			 
			//browser.fetchProperties(new SearchFilter());
			/*
			 * var url =
			 * "https://www.vivareal.com.br/imovel/sobrado-4-quartos-jardim-altos-do-itavuvu-bairros-sorocaba-com-garagem-130m2-venda-RS385000-id-2721191252/";
			 * var result = browser.fetchPropertyAdditionalInfo(url);
			 * System.out.println("Coordinates: %s, [%s]".formatted(result.getLatitude(),
			 * result.getLongitude()));
			 * System.out.println("Publisher: %s".formatted(result.getPublisher()));
			 */
		};
	}

}
