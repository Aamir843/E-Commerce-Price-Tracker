package com.nagarro.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient amazonWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8081").build();
    }

    @Bean
    public WebClient ebayWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8082").build();
    }

    @Bean
    public WebClient walmartWebClient() {
        return WebClient.builder().baseUrl("http://localhost:8083").build();
    }
}

