package com.example.springdataintroexercise;


import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;
import com.example.springdataintroexercise.seed.DataBaseSeed;
import com.example.springdataintroexercise.services.AuthorService;
import com.example.springdataintroexercise.services.BookService;
import com.example.springdataintroexercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;


@Component
public class ConsoleRunner implements CommandLineRunner {


    private final AuthorService authorService;
    private final DataBaseSeed baseSeed;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, DataBaseSeed baseSeed, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.baseSeed = baseSeed;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws IOException {
//        baseSeed.seedAuthors();
//        baseSeed.seedCategories();
//        baseSeed.seedBooks();

        //Task 1 Books after Year 2000.
//        List<Book> bookList = bookService.getBooksYearAfter2000();
//        bookList.forEach(b -> System.out.println(b.getTitle()));

        // Task 2 GetAuthorsByBooksBefore
//        List<Author> authorsByBooksBefore = this.authorService.getAuthorsByBooksBefore();
//        authorsByBooksBefore.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));

        //Task 3 getAuthorsWithBooksOrderedDesc
//        List<Author> authorByBooks = this.authorService.getAuthorByBooks();
//        authorByBooks.stream()
//                .sorted(Comparator.comparing(a -> a.getBooks().size()))
//                .forEach(author ->
//                        System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), author.getBooks().size()));

        List<Book> books = this.bookService.getBooksByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescBookTitleAsc();
        books.forEach(book -> System.out.printf("%s %s %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies()));
    }
}
