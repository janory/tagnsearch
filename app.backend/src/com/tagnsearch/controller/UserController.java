package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by JS on 8/19/16.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> listUsers() {
        final PageImpl<User> all = (PageImpl<User>) userRepository.findAll();
        return all.getContent();
    }

}
