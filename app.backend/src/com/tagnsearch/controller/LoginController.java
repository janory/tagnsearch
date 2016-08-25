package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.services.UserService;
import com.tagnsearch.utils.AuthUtils;
import com.tagnsearch.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * Created by JS on 8/21/16.
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity login(@Validated @RequestBody UserDTO userDTO) {
        final User user = userService.findByUsername(userDTO.username);
        if ( UserUtils.checkIfUserExists(user) ) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(userDTO.username + " user not found!");
        }
        if ( !UserUtils.checkIfPasswordCorrect(user, userDTO.password) ) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Username or password is wrong!");
        }
        return ResponseEntity.ok(findOrCreateToken(user));
    }

    private String findOrCreateToken(final User user) {
        return AuthUtils.hasActiveToken(user) ?
                AuthUtils.getTokenByUser(user) : AuthUtils.generateToken(user);
    }

    private static class UserDTO {
        @NotNull
        private String username;
        @NotNull
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }
    }
}
