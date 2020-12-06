
package com.dateformatchecker.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dateformatchecker"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Date Checker API",
                "To check the date format and add number of days to the date",
                "v1.0.4",
                "Terms of Services",
                new Contact("Sivasankar S","https://www.hcl.com","s.sivasan@hcl.com"),
                "License of API","API licence URL", Collections.emptyList());
    }
    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).build();
    }


    private List<SecurityReference> defaultAuth(){
        final AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Collections.singletonList(new SecurityReference("APIKey",authorizationScopes));
    }

    private ApiKey apiKey(){
        return new ApiKey("APIKey","APIKey","header");
    }
}
