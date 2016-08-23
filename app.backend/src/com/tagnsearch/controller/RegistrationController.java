package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by JS on 8/19/16.
 */

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public User createUser(@Validated @RequestBody UserDTO userDTO){
        return userService.create(createNewUser(userDTO));
    }

    private User createNewUser(final UserDTO userDTO) {
        final User newUser = new User();
        newUser.setLastName(userDTO.lastName);
        newUser.setFirstName(userDTO.firstName);
        newUser.setUsername(userDTO.username);
        newUser.setPassword(userDTO.password);
        newUser.setRole(userDTO.role);
        return newUser;
    }

    private static class UserDTO {
        @Null
        private Long id;
        @NotNull
        private String firstName;
        @NotNull
        private String lastName;
        @NotNull
        private String username;
        @NotNull
        private String password;
        @NotNull
        private String role;

        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(final String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(final String lastName) {
            this.lastName = lastName;
        }

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

        public String getRole() {
            return role;
        }

        public void setRole(final String role) {
            this.role = role;
        }
    }
}
