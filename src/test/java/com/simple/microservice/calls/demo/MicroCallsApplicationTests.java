package com.simple.microservice.calls.demo;

import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MicroCallsApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void isCollectionInitialized(){
        PostService postService = new PostService();
        boolean b= postService.getPosts() != null;
        System.err.println(b);
    }



}
