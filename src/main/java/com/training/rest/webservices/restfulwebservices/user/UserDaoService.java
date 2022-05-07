package com.training.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDaoService {
    private static List<User> users= new ArrayList<>();
    private static int userCount = 3;

    static{
        users.add(new User(1, "Nirmal", new Date()));
        users.add(new User(2, "Swati", new Date()));
        users.add(new User(3, "Samar", new Date()));
    }

    public List<User> findAll(){
    return users;
    }

    public User save(User user){
        if(user.getId() < 1 || ("ab").equals("ab")){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
    public User findOne(int id){
        Optional<User> firstUser = users.stream().filter(u -> id == u.getId()).findAny();
        return firstUser.get();
    }

    public List<User> deleteById(int id){
        List<User> userToRemove = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
        users.removeIf(u->u.getId() == id);
        if(userToRemove.size() > 0) {
            return userToRemove;
        } else {
            return null;
        }
    }
}
