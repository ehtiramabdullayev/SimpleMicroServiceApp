package com.simple.microservice.calls.demo;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MicroCallsApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void getPosts(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        PostService service = applicationContext.getBean(PostService.class);

        System.out.println("All Posts:");

        for (Post post : service.getAllPosts()) {
            System.out.println(post);
        }




    }



}
