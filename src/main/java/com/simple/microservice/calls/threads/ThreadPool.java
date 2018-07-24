package com.simple.microservice.calls.threads;


import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ThreadPool {

    public void test() {
        Arrays.asList(1, 2, 3).parallelStream().peek(data -> System.out.println(Thread.currentThread().getName() + data));


    }

}
