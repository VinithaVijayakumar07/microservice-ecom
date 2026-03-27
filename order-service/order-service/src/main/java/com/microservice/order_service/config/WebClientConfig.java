package com.microservice.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webCliemtBuilder(){
        return WebClient.builder();
    }

    //this config class is for inter communication between 2 microservices order and product
}
