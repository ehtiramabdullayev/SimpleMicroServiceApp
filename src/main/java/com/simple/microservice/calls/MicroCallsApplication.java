package com.simple.microservice.calls;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@SpringBootApplication
public class MicroCallsApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SpringApplication.run(MicroCallsApplication.class, args);


    }
}
