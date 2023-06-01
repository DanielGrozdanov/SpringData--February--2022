package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Book;

import java.util.List;

public interface BookService {

    void save(Book book);

    List<Book> getBooksYearAfter2000();

    List<Book> getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescBookTitleAsc();
}
