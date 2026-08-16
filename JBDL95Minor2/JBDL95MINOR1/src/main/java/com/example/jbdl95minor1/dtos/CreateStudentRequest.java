package com.example.jbdl95minor1.dtos;

import com.example.jbdl95minor1.models.Gender;
import com.example.jbdl95minor1.models.Student;
import com.example.jbdl95minor1.models.StudentStatus;
import com.example.jbdl95minor1.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @Email
    private String email;

    @NonNull
    private Gender gender;

    public Student toStudent(){
        return Student.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .status(StudentStatus.ACTIVE)
                .user(User.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
