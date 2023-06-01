package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void saveAuthor(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public Author getRandomAuthor() {
        return this.authorRepository.getRandomAuthor();
    }

    @Override
    public List<Author> getAuthorsByBooksBefore() {
        return this.authorRepository.findDistinctByBooksReleaseDateBefore(LocalDate.of(1990, 1, 1));
    }

    @Override
    public List<Author> getAuthorByBooks() {
        return this.authorRepository.findAll();
    }
}
