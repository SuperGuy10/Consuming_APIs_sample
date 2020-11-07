package com.chad.practice.consuming_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumingApiApplication.class, args);
    }
    @Bean
    public RestTemplate startRT(){
        return new RestTemplate();
    }

}
