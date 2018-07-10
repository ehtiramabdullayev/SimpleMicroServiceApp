package com.simple.microservice.calls.demo;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.service.PostService;
import com.simple.microservice.calls.service.PostServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MicroCallsApplicationTests {



//    @Test
//    public void postANewPost(){
//        System.out.println("Adding a Post");
//        Post p = new Post();
//        p.setAuthor("Chris1243534");
//        p.setTitle("New Title23232345345");
//        HttpStatus status = postService.addPost(p);
//        //System.out.println("Response = " + status);
//
//    }


    @Test
    public void testGetPostsBehaviour(){
        PostService service = Mockito.mock(PostService.class);
        service.addPost(new Post(1,"first","V"));
        service.addPost(new Post(2,"second","X"));

        Mockito.verify(service).addPost(new Post(1,"first","V"));
        Mockito.verify(service).addPost(new Post(1,"second","X"));
    }


    @Test
    public void getAllPostsCalledOnce(){
        RestTemplate tempMock = Mockito.mock(RestTemplate.class);
        PostService postService = new PostServiceImpl(tempMock);
        Post[] posts = new Post[1];
        posts[0]=new Post(1,"Qwerty","Post");
        ResponseEntity<Post[]> entity = Mockito.mock(ResponseEntity.class);
        Mockito.when(entity.getBody()).thenReturn(posts);
        //
        Mockito.when(tempMock.getForEntity(Mockito.anyString(),Mockito.any(Post[].class)).thenReturn();

        List<Post> postList = postService.getAllPosts();

        Assert.assertEquals("passed",1,postList.get(0).getId());
    }



}
