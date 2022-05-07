package com.training.rest.webservices.restfulwebservices.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    private final UserRepository userRepository;

    public UserJPAResource(UserRepository userRepository) {
         this.userRepository = userRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        List<User> all = userRepository.findAll();
        if(all.size() == 0){
            throw new UserNotFoundException("No Users exists!");
        }
        return all;
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){

        Optional<User> usr = userRepository.findById(id); //JPA supports findById
        if(!usr.isPresent()){
            throw new UserNotFoundException("id-"+ id);
        }
        EntityModel<User> model = EntityModel.of(usr.get());
        WebMvcLinkBuilder linkToUsers =
                linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users")); // add more links here
        return model;
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        //We don't need to return any value, as long as http status 200, delete is successful
        Optional<User> usr = userRepository.findById(id);
        if(!usr.isPresent()){
            throw new UserNotFoundException("id-"+ id);
        }
        userRepository.deleteById(id);
        }


    @PostMapping("/jpa/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        // let user know that the data is created
        // /user/{id}   savedUser.getId()
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        System.out.println(location);
        return ResponseEntity.created(location).build();
    }
}
