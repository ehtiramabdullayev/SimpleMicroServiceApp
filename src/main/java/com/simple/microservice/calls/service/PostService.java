package com.simple.microservice.calls.service;

import com.simple.microservice.calls.bean.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostService {

    private Map<Long,Post> posts ;

    public List<Post> getAllPosts(){
        return new ArrayList<>(posts.values());
    }

    @Autowired
    public Map<Long, Post> getPosts() {
        return posts;
    }


    @Autowired
    public void setPosts(Map<Long, Post> posts) {
        this.posts = posts;
    }
}
