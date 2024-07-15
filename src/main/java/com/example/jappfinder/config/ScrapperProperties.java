package com.example.jappfinder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "scrapper")
@Getter @Setter
public class ScrapperProperties {
	private String server;
	private String username;
	private String password;
}
