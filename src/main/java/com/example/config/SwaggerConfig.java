package com.example.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().useDefaultResponseMessages(false).apiInfo(this.apiInfo())
				.securitySchemes(Arrays.asList(apiKey()));
	}

	//Comentado para testar o apiKey()
	/*private ArrayList<Parameter> getGlobalOperationParameters() {
		ArrayList<Parameter> parameters = new ArrayList<>();

		parameters.add(new ParameterBuilder().name("Authorization").description("Authenticação JWT")
				.modelRef(new ModelRef("string")).parameterType("header").required(true).build());

		return parameters;
	}*/

	private ApiKey apiKey() {
		return new ApiKey("authkey", "Authorization", "header");
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("SEIA - Fiscalização - backend", "Serviços Rest para a aplicação Seia - Fiscalização",
				"API TOS", "Termos de Serviços",
				new Contact("Túlio Adorno", "www.example.com", "myeaddress@company.com"), "License of API",
				"API license URL", Collections.emptyList());
	}
}