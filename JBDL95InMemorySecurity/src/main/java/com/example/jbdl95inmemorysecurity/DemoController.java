package com.example.jbdl95inmemorysecurity;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

//    user authority
    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User";
    }

//    admin authority
    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin";
    }

    @GetMapping("/admin/user/hello")
    public String adminUserHello() {
        return "Hello Admin";
    }

}


/*
*
* Pros:-
* more than 1 user
* user defined passwod
* faster  access
*
* Cons:-
*
* 1. Limited storage
* 2. Distributed systems will have problem (load balancer)
* 3. Multiple copies (replication)
*
*
* */
