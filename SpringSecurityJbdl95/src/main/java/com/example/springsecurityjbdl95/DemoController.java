package com.example.springsecurityjbdl95;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {


    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}

/*
*
* By default every api is secure by spring security
* if you want to make some api public then you will have code it out
*
* Cons:-
* 1. For all apis it will be secure
* 2. we cannot have a user defined password as it is always generating
* 3. We can only have single user
*
*
* */
