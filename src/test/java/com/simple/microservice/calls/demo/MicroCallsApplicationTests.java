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
    private RestTemplate tempMock;

    private PostService postService;

    private List<Post> postList;


    public void givenARestTemplate(){

        tempMock = Mockito.mock(RestTemplate.class);
    }

    //given

    @Test
    public void getAllPostsCalledOnce(){
        //All given
        givenARestTemplate();
        givenPostService();

        Post[] posts = new Post[1];
        posts[0]=new Post(1,"Qwerty","Post");
        ResponseEntity<Post[]> entity = Mockito.mock(ResponseEntity.class);
        Mockito.when(entity.getBody()).thenReturn(posts);
        Mockito.doReturn(entity).when(tempMock).getForEntity(Mockito.anyString(), Mockito.any());

        //When
        whenCallingPostService();

        //then
        whenCallPostServiceForGetAllPosts();
    }

    //then
    private void whenCallPostServiceForGetAllPosts(){
        Assert.assertEquals("passed",1,postList.get(0).getId());
    }


    private void givenPostService(){
        postService = new PostServiceImpl(tempMock);
    }

    private void whenCallingPostService(){
        postList = postService.getAllPosts();
    }

}
