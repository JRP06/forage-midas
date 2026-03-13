package com.jpmc.midascore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


    //configuration class - holds @Bean definition for Srping to manage
    @Configuration
    public class AppConfig {
        //Registers RestTemplate as a Spring managed object
        //RestTemplate is Spring's built-in tool for calling external APIs
        @Bean
        public RestTemplate restTemplate(){
            //create and return a new RestTemplate instance
            //Spring stores this in its warehouse and injects it wherever needed
            return new RestTemplate();
        }

    }
