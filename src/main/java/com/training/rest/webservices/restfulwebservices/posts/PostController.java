package com.training.rest.webservices.restfulwebservices.posts;

import com.training.rest.webservices.restfulwebservices.user.User;
import com.training.rest.webservices.restfulwebservices.user.UserNotFoundException;
import com.training.rest.webservices.restfulwebservices.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @GetMapping(path="/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){
        //User uId = userRepository.getById(id);
        Optional<User> uId = userRepository.findById(id);
        if(!uId.isPresent()){
            throw new UserNotFoundException("id-"+ id);
        }
        return uId.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity createPosts(@Valid @RequestBody Post post, @PathVariable int id){
        Optional<User> uId = userRepository.findById(id);
        if(!uId.isPresent()){
            throw new UserNotFoundException("id-"+ id);
        }
        post.setUser(uId.get()); //user retrieved from DBuI
        postRepository.save(post);
        // let user know that the data is created
        // /user/{id}   savedUser.getId()
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getPost_id()).toUri();
        System.out.println(location);
        return ResponseEntity.created(location).build();
    }
}
