package com.hkcoding.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("OCO Email Service API")
        .description("This API enables consumers to send Plain and MIME emails via SMTP server")
        .contact(new Contact("Team Name", "", "team@email.com"))
        .build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.hkcoding.admin.api"))
        .build()
        .apiInfo(apiInfo())
        .tags(new Tag("oco-email-service", "OCO Email Service API"));
  }
}
