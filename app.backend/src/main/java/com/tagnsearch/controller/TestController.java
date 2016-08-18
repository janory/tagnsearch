package com.tagnsearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by JS on 8/11/16.
 */
@Controller
public class TestController {

    @RequestMapping(value = "/alma" )
    @ResponseBody
    public String hi(){
        return "indexa.html";
    }

}
