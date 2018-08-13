package com.simple.microservice.calls;


import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroCallsApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SpringApplication.run(MicroCallsApplication.class, args);
    }
}
