package com.example.springdataintroexercise.services;

import com.example.springdataintroexercise.entities.Book;
import com.example.springdataintroexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksYearAfter2000() {
        return this.bookRepository.findBookByReleaseDateAfter(LocalDate.of(2000, 1, 1));
    }

    @Override
    public List<Book> getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescBookTitleAsc() {
        String authorFirstName = "George";
        String authorLastName = "Powell";
        return this.bookRepository.getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(authorFirstName, authorLastName);
    }
}
