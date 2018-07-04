package com.simple.microservice.calls.bean;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class Post {

    private long id;
    private String title;
    private String message;
    private Date created;
    private String author;
    private Map<Long, Comment> comments;


    public Post() {

    }

    public Post(long id, String title, String message, Date created, String author) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.created = created;
        this.author = author;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Map<Long, Comment> getComments() {
        return comments;
    }

    public void setComments(Map<Long, Comment> comments) {
        this.comments = comments;
    }
}
