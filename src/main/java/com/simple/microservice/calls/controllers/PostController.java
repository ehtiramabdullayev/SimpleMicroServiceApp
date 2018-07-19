package com.simple.microservice.calls.controllers;

import com.simple.microservice.calls.bean.Post;
import com.simple.microservice.calls.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private PostService postService;


    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }


    @GetMapping(value = "posts", produces = "application/json")
    public List<Post> getPosts() {
        return postService.getAllPosts().stream().filter(post -> post.getId() > 4L).collect(Collectors.toList());
    }

}
