package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JS on 8/19/16.
 */

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public User createUser(@RequestBody User userDTO){
        return userService.create(userDTO);
    }
}
