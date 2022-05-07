package com.training.rest.webservices.restfulwebservices.posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.rest.webservices.restfulwebservices.user.User;

import javax.persistence.*;


@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer post_id;
    private String description;
    @ManyToOne(fetch= FetchType.LAZY)
    @JsonIgnore  //when POST happens we don't need to create user, it will create recursive issues
    private User user;

    public Post(Integer post_id, String description, User user) {
        this.post_id = post_id;
        this.description = description;
        this.user = user;
    }

    protected Post() {}

    public Integer getPost_id() {
        return post_id;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", description='" + description + '\'' +
                '}';
    }
}
