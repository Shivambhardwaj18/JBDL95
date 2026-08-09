package com.example.jbdl95indbsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private DemoUserDetailsService demoUserDetailsService;

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


    @PostMapping("/user/create")
    public void createUser(@RequestParam String username, @RequestParam String password) {

        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("USER")
                .build();

        this.demoUserDetailsService.create(demoUser);
    }

    @PostMapping("/admin/create")
    public void createAdmin(@RequestParam String username, @RequestParam String password) {

        DemoUser demoUser = DemoUser.builder()
                .username(username)
                .password(password)
                .authorities("ADMIN")
                .build();

        this.demoUserDetailsService.create(demoUser);
    }
}

//UserDetailsService

/*
*
* client sends username and password
* B,E :-
*
* first your spring sec will fetch the user by username (username must be unique)
*
* then once spring sec has user he can access user password
*
* but to match password spring need password encoder
*
* you need to provide this service and encpder
*
*
*
* */
