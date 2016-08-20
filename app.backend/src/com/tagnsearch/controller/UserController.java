package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.repositories.UserRepository;
import com.tagnsearch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by JS on 8/19/16.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User getUserDetails(@PathVariable Long id) {
        return userService.findById(id);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public User createUser(@RequestBody User user){
        final User newUser = new User();
        newUser.setLastName(user.getLastName());
        newUser.setFirstName(user.getFirstName());
        newUser.setPassword(user.getPassword());
        return userService.createOrUpdateUser(newUser);
    }

}
