package com.testingjava8.videolibrary.config;

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
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.testingjava8.videolibrary"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());

                // RequestHandlerSelectors.any() also possible
                // PathSelectors.ant("/foos/*")  instead of setting it to ANT in application.properties
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder().title("JavaInUse API")
                .title("Video Library Rent Application")
                .description("Swagger configuration for application")
                .termsOfServiceUrl("https://github.com/ionknowmyname")
                .license("Springboot License")
                .licenseUrl("https://github.com/ionknowmyname").version("1.0").build();
    }





    /*@Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/posts.*"), regex("/api/javainuse.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("JavaInUse API")
                .description("JavaInUse API reference for developers")
                .termsOfServiceUrl("http://javainuse.com")
                .contact("javainuse@gmail.com").license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com").version("1.0").build();
    }*/
}
