package com.training.rest.webservices.restfulwebservices.user;

import com.training.rest.webservices.restfulwebservices.posts.Post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue //hibernate by default start the sequence from 1
    private int id;
    @Size(min=2, message = "char must be more than one")
    private String name;
    @Past
    private Date dob;
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(int id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    protected User(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDob() {
        return dob;
    }

    public void setId(int id) {
        this.id = id;
    }
    //@Size(min=2, message="Name should have at least 2 characters")
    public void setName(String name) {
        this.name = name;
    }
    //@Past
    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
