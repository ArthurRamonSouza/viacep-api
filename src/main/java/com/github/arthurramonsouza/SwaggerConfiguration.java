package com.github.arthurramonsouza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Class configure the Swagger and the annotations below
 * indicates to Spring set the configurations and enable the Swagger.
 * 
 * @author ArthurRamonSouza
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any()) //RequestHandlerSelectors.basePackage("com.github.arthurramonsouza")
				.paths(PathSelectors.any()) //PathSelectors.ant("/**")
				.build();
	}
}