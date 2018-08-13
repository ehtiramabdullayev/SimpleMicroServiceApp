package com.simple.microservice.calls.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simple.microservice.calls.service.PostService;

@Controller

public class PostController {

    private PostService postService;

    @Autowired
    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/home")
    public String getIndexPage(Map<String, Object> model) {
        System.out.println("Test");
        model.put("posts", postService.getAllPosts());
        return "index";
    }


    @RequestMapping("/post")
    public String postMessage(Map<String, Object> model) {
        System.out.println("Post");
        model.put("posts", postService.getAllPosts());
        return "post";
    }



}
