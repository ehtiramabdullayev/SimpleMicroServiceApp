package com.simple.microservice.calls.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Post {

    private long id;
    private String title;
    private String author;
    private String created;
    private String comments;
    private String message;

    public Post() {
    }

    public Post(long id, String title, String author, String created, String comments, String message) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.created = created;
        this.comments = comments;
        this.message = message;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id &&
                Objects.equals(title, post.title) &&
                Objects.equals(author, post.author) &&
                Objects.equals(created, post.created) &&
                Objects.equals(comments, post.comments) &&
                Objects.equals(message, post.message);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, author, created, comments, message);
    }
}
