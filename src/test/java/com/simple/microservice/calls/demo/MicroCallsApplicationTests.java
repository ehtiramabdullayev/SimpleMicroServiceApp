package com.simple.microservice.calls.demo;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.bean.ResponsePost;
import com.simple.microservice.calls.config.AppConfig;
import com.simple.microservice.calls.exception.OperationIsNotSuccessfulException;
import com.simple.microservice.calls.exception.OperationPartiallySuccessfulException;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public class MicroCallsApplicationTests {

    private RestTemplate tempMock;

    private PostService postService;

    private List<Post> postList;

    private ResponseEntity<HttpStatus> responseEntity;


    public void givenARestTemplate() {

        tempMock = Mockito.mock(RestTemplate.class);
    }

    //given

    @Test
    public void getAllPostsCalledOnce() {
        //All given
        givenARestTemplate();
        givenPostService();

        Post[] posts = new Post[1];
        posts[0] = new Post(1, "Qwerty", "Post","Created","Comment","Message");
        ResponseEntity<Post[]> entity = Mockito.mock(ResponseEntity.class);
        Mockito.when(entity.getBody()).thenReturn(posts);
        Mockito.doReturn(entity).when(tempMock).getForEntity(Mockito.anyString(), Mockito.any());

        //when
        whenCallingPostService();

        //then
        whenCallPostServiceForGetAllPosts();
    }


    //
    @Test
    public void testAddPost() {
        givenARestTemplate();
        givenPostService();
        ResponseEntity<HttpStatus> responseEntity = Mockito.mock(ResponseEntity.class);
        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        Mockito.doReturn(responseEntity).when(tempMock).postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any());

        try {
            postService.addPost(null);
        } catch (Exception e) {
            Assert.fail();
        }

    }

    @Test(expected = OperationIsNotSuccessfulException.class)
    public void testAddPostWithException() {
        givenARestTemplate();
        givenPostService();
        givenAMockResponseWithStatus(HttpStatus.BAD_REQUEST);

        Mockito.doReturn(responseEntity).when(tempMock).postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any());

        postService.addPost(null);
    }

    private void givenAMockResponseWithStatus(HttpStatus status) {
        responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(responseEntity.getStatusCode()).thenReturn(status);
    }

    @Test(expected = OperationIsNotSuccessfulException.class)
    public void testAddMultiplePostsWithBadStatusCodeException() {
        givenARestTemplate();
        givenPostService();

        ResponsePost responsePost = new ResponsePost(2);
        ResponseEntity<ResponsePost> responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(responseEntity.getBody()).thenReturn(responsePost);
        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        Mockito.doReturn(responseEntity).when(tempMock).postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any());

        postService.addMultiplePosts(Collections.singletonList(new Post(1, "Qwerty", "Post","Created","Comment","Message")));

    }


    @Test(expected = OperationPartiallySuccessfulException.class)
    public void testAddMultiplePostsPartiallyAddedException() {
        givenARestTemplate();
        givenPostService();
        Post[] posts = {new Post(0, "Qwerty", "Post","Created","Comment","Message"),
                        new Post(0, "Qwerty", "Post","Created","Comment","Message"),
                        new Post(0, "Qwerty", "Post","Created","Comment","Message"),};

        ResponsePost responsePost = new ResponsePost(4);
        ResponseEntity<ResponsePost> responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(responseEntity.getBody()).thenReturn(responsePost);
        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        Mockito.doReturn(responseEntity).when(tempMock).postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any());

        List<Post> postArray = Arrays.asList(posts);
        postService.addMultiplePosts(postArray);

    }


    @Test
    public void testAddMultiplePostsWithSuccess() {
        givenARestTemplate();
        givenPostService();

        ResponsePost responsePost = new ResponsePost(1);
        ResponseEntity<ResponsePost> responseEntity = Mockito.mock(ResponseEntity.class);

        Mockito.when(responseEntity.getBody()).thenReturn(responsePost);
        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        Mockito.doReturn(responseEntity).when(tempMock).postForEntity(Mockito.anyString(), Mockito.any(), Mockito.any());

        postService.addMultiplePosts(Collections.singletonList(new Post(1,"Qwerty", "Post","Created","Comment","Message")));

    }


    @Test
    public void testDeletePostWithSuccess() {
        givenARestTemplate();
        givenPostService();

        Post responsePost = new Post(1,"Qwerty", "Post","Created","Comment","Message");
        ResponseEntity<Post> responseEntity = Mockito.mock(ResponseEntity.class);


        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        Mockito.when(responseEntity.getBody()).thenReturn(responsePost);

        Mockito.doReturn(responseEntity).when(tempMock).exchange(Mockito.any(URI.class),
                                                                 Mockito.eq(HttpMethod.DELETE),
                                                                 Mockito.any(),
                                                                 Mockito.eq(Post.class));

        Assert.assertEquals(postService.deletePost(1),1);
    }


    @Test(expected = OperationIsNotSuccessfulException.class)
    public void testDeletePostWithException() {
        givenARestTemplate();
        givenPostService();

        Post responsePost = new Post(1, "Qwerty", "Post","Created","Comment","Message");
        ResponseEntity<Post> responseEntity = Mockito.mock(ResponseEntity.class);


        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        Mockito.when(responseEntity.getBody()).thenReturn(responsePost);

        Mockito.doReturn(responseEntity).when(tempMock).exchange(Mockito.any(URI.class),
                Mockito.eq(HttpMethod.DELETE),
                Mockito.any(),
                Mockito.eq(Post.class));

      postService.deletePost(1);


    }



    //then
    private void whenCallPostServiceForGetAllPosts() {
        Assert.assertEquals("passed", 1, postList.get(0).getId());
    }


    private void givenPostService() {
        postService = new PostServiceImpl(tempMock);
    }

    private void whenCallingPostService() {
        postList = postService.getAllPosts();
    }

}
