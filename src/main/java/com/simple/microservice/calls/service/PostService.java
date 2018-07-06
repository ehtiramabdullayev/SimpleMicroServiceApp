package com.simple.microservice.calls.service;

import com.simple.microservice.calls.bean.Post;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getById(int id);
    HttpStatus addPost(Post post);
    void updatePost(Post post);
    void deletePost(int id);
}
