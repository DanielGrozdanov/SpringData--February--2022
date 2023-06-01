package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    void saveAuthor(Author author);

    Author getRandomAuthor();

    List<Author> getAuthorsByBooksBefore();

    List<Author> getAuthorByBooks();
}
