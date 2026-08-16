package com.example.jbdl95minor1.dtos;


import com.example.jbdl95minor1.models.Admin;
import com.example.jbdl95minor1.models.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAdminRequest {



    private String username;

    private String password;

    private String name;

    public Admin toAdmin() {
        return Admin.builder()
                .name(name)
                .user(
                        User.builder()
                                .username(username)
                                .password(password)
                                .build()
                )
                .build();
    }
}
