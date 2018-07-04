package com.simple.microservice.calls.config;

import com.simple.microservice.calls.bean.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public Post post(){
        return new Post();
    }
}
