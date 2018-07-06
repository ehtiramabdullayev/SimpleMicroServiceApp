package com.simple.microservice.calls.demo;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MicroCallsApplicationTests {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    PostService service = applicationContext.getBean(PostService.class);

    @Test
    public void contextLoads() {
    }


    @Test
    public void getPosts(){

        System.out.println("All Posts:");
        for (Post post : service.getAllPosts()) {
            System.out.println(post);
        }
    }

    @Test
    public void getPostWhereIdIsOne(){
        System.out.println(service.getById(1));
    }


    @Test
    public void deleteGivenPostWithIdOne(){
        service.deletePost(1);

    }

    @Test
    public void postANewPost(){
        System.out.println("Adding a Post");
        Post p = new Post();
        p.setAuthor("Chris1243534");
        p.setTitle("New Title23232345345");
        HttpStatus status = service.addPost(p);
        //System.out.println("Response = " + status);

    }

}
