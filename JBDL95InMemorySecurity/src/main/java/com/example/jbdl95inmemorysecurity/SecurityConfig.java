package com.example.jbdl95inmemorysecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


//    authority -> "USER"
//    role -> "ROLE_USER"

// u need to have a password encoder to use spring security with password
//    In memory authentication
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails user1 = User.builder()
                .username("fred")
                .password(passwordEncoder().encode("fred@123"))
                .authorities("USER")
                .build();

        UserDetails user2 = User.builder()
                .username("john")
                .password("$2a$09$zZTRENYA0qIZaj.zmGxjHuXN5znyGWDCxczEj/Yft63YhfQCRGxke")
                .authorities("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);

    }


//    authorisation

//    order of request matchers is important
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/admin/user/**").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/hello").permitAll()

                )
                .formLogin(Customizer.withDefaults());


        return httpSecurity.build();
    }


//    min strength = 4
//    max strength = 31
//    default = -1 = 10

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


}


//difference between encoding and serialisation
/*
*
* from serailse you can deserialse
* but once encoded you cannot decode
* */
