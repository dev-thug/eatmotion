package com.one.eatmotion.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swaggerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(swaggerInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.one.eatmotion.controller"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false);
  }

  private ApiInfo swaggerInfo() {
    return new ApiInfoBuilder()
        .title("맛집을 구해줘 API Documentation")
        .description("서버 API에 대한 연동 문서")
        .license("")
        .licenseUrl("")
        .version("1")
        .build();
  }
}
