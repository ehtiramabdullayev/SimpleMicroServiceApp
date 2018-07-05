package com.simple.microservice.calls.start;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class MicroCallsApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroCallsApplication.class, args);
    }
}
