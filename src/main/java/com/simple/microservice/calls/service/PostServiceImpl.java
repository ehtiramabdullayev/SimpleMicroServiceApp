package com.simple.microservice.calls.service;


import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.bean.ResponsePost;
import com.simple.microservice.calls.exception.OperationIsNotSuccessfulException;
import com.simple.microservice.calls.exception.OperationPartiallySuccessfulException;

@Service
public class PostServiceImpl implements PostService {

    static final String ROOT_URI = "http://localhost:3000/posts";
    private final RestTemplate restTemplate;

    @Autowired
    public PostServiceImpl(@Qualifier("resttemplate2") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
//    @HystrixCommand(fallbackMethod = "reliable")
    public List<Post> getAllPosts() {
        ResponseEntity<Post[]> posts = restTemplate.getForEntity(ROOT_URI, Post[].class);
        return Arrays.asList(posts.getBody());
    }
    
    public List<Post> reliable() {
        List<Post> a = new ArrayList<>();
        a.add(new Post(1,"title","author","created","comments","message"));
        return a;
      }

    @Override
    public Post getById(int id) {
        ResponseEntity<Post> post = restTemplate.getForEntity(ROOT_URI + "/" + id, Post.class);
        return post.getBody();
    }

    @Override
    public void addPost(Post post) {
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(ROOT_URI, post, Void.class);

        if (responseEntity.getStatusCode() != HttpStatus.CREATED) {
            throw new OperationIsNotSuccessfulException("The operation is failed, returned status code is " + responseEntity.getStatusCode());
        }
    }

    @Override
    public int addMultiplePosts(List<Post> posts) {
        ResponseEntity<ResponsePost> responseEntity = restTemplate.postForEntity(ROOT_URI, posts, ResponsePost.class);
        int addedPosts = 0;
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            final ResponsePost responsePost = responseEntity.getBody();
            if (responsePost.getNumberOfPostWereInserted() != posts.size()) {
                throw new OperationPartiallySuccessfulException("The actual number of inserted post were: " +
                        responsePost.getNumberOfPostWereInserted());
            } else {
                addedPosts = responsePost.getNumberOfPostWereInserted();

            }
        } else {
            throw new OperationIsNotSuccessfulException("The operation is failed ,returned status code is " + responseEntity.getStatusCode());
        }
        return addedPosts;
    }


    @Override
    public void updatePost(Post post) {
        restTemplate.put(ROOT_URI, post);
    }

    @Override
    public int deletePost(int id) {
        URI url = URI.create(ROOT_URI + "/" + id);
        ResponseEntity<Post> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, null, Post.class);

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new OperationIsNotSuccessfulException("The operation is failed ,returned status code is " + responseEntity.getStatusCode()); 
        }
        return id;
    }

}
