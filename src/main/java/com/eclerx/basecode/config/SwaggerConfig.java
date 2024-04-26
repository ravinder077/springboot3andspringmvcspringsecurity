package com.eclerx.basecode.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

	public static final String title = "Eclerx";
	public static final String description = "Eclerx";
	public static final String version = "0.1";
	public static final String termsOfServiceUrl = "https://eclerx.com";
	public static final String contactName = "01234-56789";
	public static final String license = "eclerx.com";
	public static final String licenseUrl = "https://eclerx.com";

	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
				.components(new Components().addSecuritySchemes("bearer-jwt",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title(title).description(description).version(version)
						.termsOfService(termsOfServiceUrl).contact(new Contact().name(contactName))
						.license(new License().name(license).url(licenseUrl)))
				.externalDocs(new ExternalDocumentation().url(licenseUrl).description(description))
				.addSecurityItem(new SecurityRequirement().addList("bearer-jwt", "Authorization"));
	}
}
