package com.travelport.api.flightdata.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.Duration;
import java.util.Collections;

@Configuration
public class SpringFoxConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.travelport.api.flightdata"))
        .paths(PathSelectors.any())
        .build().apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "REST API FOR FLIGHT DETAILS",
        "Service for getting Flight Details",
        "API TOS",
        "Terms of service",
        new Contact(" Nilesh Bukane", "", "bukanenilesh@gmail.com"),
        "License of API",
        "API license URL",
        Collections.emptyList());
  }

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {

    return builder.setConnectTimeout(Duration.ofMillis(300000))
            .setReadTimeout(Duration.ofMillis(300000)).build();
  }


}
