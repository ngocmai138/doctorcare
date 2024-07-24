package com.asm3.prj321.doctorcare.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customerOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Doctorcare API")
						.version("1.0")
						.description("API documentation for Doctorcare project"));
	}
	
	@Bean
	public GroupedOpenApi allApis() {
		return GroupedOpenApi.builder()
				.group("all-apis")
				.pathsToMatch("/**")
				.build();
	}
	
}
