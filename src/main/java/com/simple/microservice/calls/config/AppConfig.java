package com.simple.microservice.calls.config;


import com.simple.microservice.calls.bean.Post;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.simple.microservice.calls")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new com.fasterxml.jackson.databind.ObjectMapper());
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }

}
