package com.example.jappfinder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.jappfinder.driver.BrowserDriver;
import com.example.jappfinder.driver.BrowserDriverImpl;
import com.example.jappfinder.driver.SearchFilter;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan
public class JAppFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(JAppFinderApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			var beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}
			
			
			var browser = new BrowserDriverImpl();
			browser.fetchProperties(new SearchFilter());
		};
	}

}
