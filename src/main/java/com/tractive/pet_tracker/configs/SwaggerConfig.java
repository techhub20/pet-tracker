package com.tractive.pet_tracker.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/* Swagger Config for API's DOCs */
@Configuration
public class SwaggerConfig {

    
 @Bean
    public OpenAPI swaggerAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Pet Tracking Application")
                .version("1.0")
                .description("API documentation for tracking cats and dogs and their zones."));
    
    }



    
}
