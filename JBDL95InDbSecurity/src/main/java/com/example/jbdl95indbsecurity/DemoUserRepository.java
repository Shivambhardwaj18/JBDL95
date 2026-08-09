package com.example.jbdl95indbsecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface DemoUserRepository extends JpaRepository<DemoUser, Long> {


    UserDetails findByUsername(String username);
}
