package com.training.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    private final UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        List<User> all = service.findAll();
        if(all.size() == 0){
            throw new UserNotFoundException("No Users exists!");
        }
        return all;
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){

        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException("id-"+ id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers =
                linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users")); // add more links here
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        //We don't need to return any value, as long as http status 200, delete is successful
        List<User> u = service.deleteById(id);
        if(u == null){
            throw new UserNotFoundException("id-"+ id);
            }
        }


    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
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
