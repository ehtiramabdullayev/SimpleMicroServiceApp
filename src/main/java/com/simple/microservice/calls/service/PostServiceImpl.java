package com.simple.microservice.calls.service;


import com.simple.microservice.calls.bean.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final RestTemplate restTemplate;

    @Autowired
    public PostServiceImpl(RestTemplate restTemplate ) {
        this.restTemplate = restTemplate;
    }

    final String ROOT_URI = "http://localhost:3000/posts";

    @Override
    public List<Post> getAllPosts() {
        ResponseEntity<Post[]> posts = restTemplate.getForEntity(ROOT_URI, Post[].class);
        return Arrays.asList(posts.getBody());
    }

    @Override
    public Post getById(int id) {
        ResponseEntity<Post> post = restTemplate.getForEntity(ROOT_URI+"/"+id, Post.class);
        return post.getBody();
    }

    @Override
    public HttpStatus addPost(Post post) {
        ResponseEntity<HttpStatus> responseEntity = restTemplate.postForEntity(ROOT_URI,post,HttpStatus.class);
        return responseEntity.getBody();
    }

    @Override
    public void updatePost(Post post) {
        restTemplate.put(ROOT_URI,post);
    }

    @Override
    public void deletePost(int id) {
        restTemplate.delete(ROOT_URI+"/"+id);
    }

}
