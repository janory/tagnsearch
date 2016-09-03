package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.services.UserService;
import com.tagnsearch.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JS on 8/19/16.
 */

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUserDetails(@PathVariable String username) {
        final User user = userService.findByUsername(username);
        if (UserUtils.checkIfUserExists(user)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(username + " user not found!");
        }
        return ResponseEntity.ok(user);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public User updateUser(@RequestBody User userDTO){
        return userService.update(userDTO);
    }

}
