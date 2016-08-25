package com.tagnsearch.controller;

import com.tagnsearch.entities.User;
import com.tagnsearch.utils.AuthUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

/**
 * Created by JS on 8/21/16.
 */

@RestController
@RequestMapping("/logout")
public class LogoutController {

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public void logoutUser(@RequestHeader("Authorization") String authHeader,
                           @RequestAttribute("claims") Claims claims){
        final String username = (String) claims.get("sub");
        User user = new User();
        user.setUsername(username);
        AuthUtils.removeToken(user);
    }
}
