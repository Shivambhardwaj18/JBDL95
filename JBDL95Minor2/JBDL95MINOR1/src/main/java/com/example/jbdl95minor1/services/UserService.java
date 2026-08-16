package com.example.jbdl95minor1.services;

import com.example.jbdl95minor1.models.Authority;
import com.example.jbdl95minor1.models.User;
import com.example.jbdl95minor1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User createUser(User user, Authority authority){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(authority);
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
