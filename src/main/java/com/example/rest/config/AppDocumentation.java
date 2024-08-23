package com.example.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
@OpenAPIDefinition
public class AppDocumentation {

	Info info() {
		return new Info()
				.title("User Mangement System")
				.version("v1")
				.description("**A simple CRUD API Managing data built on REST architectural style**");		
	}

	@Bean
	OpenAPI openAPI(){
		return new OpenAPI().info(info());
	}
}
