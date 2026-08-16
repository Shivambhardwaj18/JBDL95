package com.example.jbdl95minor1.dtos;

import com.example.jbdl95minor1.models.Author;
import com.example.jbdl95minor1.models.Book;
import com.example.jbdl95minor1.models.Genre;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    private String name;

    private Genre genre;

    private String authorName;

    private String email;

    public Book toBook(){

        return Book.builder()
                .name(name)
                .genre(genre)
                .author(Author.builder()
                        .name(authorName)
                        .email(email)
                        .build())
                .build();

    }
}
