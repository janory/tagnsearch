package com.tagnsearch.controller;

import com.tagnsearch.utils.AuthUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JS on 8/21/16.
 */

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void logoutUser(@RequestHeader("Authorization") String authHeader) {
        final String token = authHeader.substring(7);
        AuthUtils.removeToken(token);
    }
}
