package com.example.springdataintroexercise.seed;

import com.example.springdataintroexercise.entities.Author;
import com.example.springdataintroexercise.entities.Book;
import com.example.springdataintroexercise.entities.Category;
import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import com.example.springdataintroexercise.services.AuthorService;
import com.example.springdataintroexercise.services.BookService;
import com.example.springdataintroexercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DataBaseSeed {
    private static final String RESOURCE_PATH = "D:\\Desktop\\Java Refresh\\SpringData\\SpringDataIntroExercise\\files\\";
    private static final String BOOKS_FILE_NAME = "books.txt";
    private static final String AUTHORS_FILE_NAME = "authors.txt";
    private static final String CATEGORIES_FILE_NAME = "categories.txt";

    private AuthorService authorService;
    private CategoryService categoryService;
    private BookService bookService;


    @Autowired
    public DataBaseSeed(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    public void seedCategories() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORIES_FILE_NAME))
                .forEach(row -> {
                    Category category = new Category(row);
                    this.categoryService.saveCategory(category);
                });
    }

    public void seedAuthors() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME))
                .forEach(row -> {
                    String[] split = row.split(" ");
                    String authorFirstName = split[0];
                    String authorLastName = split[1];
                    Author author = new Author(authorFirstName, authorLastName);
                    this.authorService.saveAuthor(author);
                });
    }

    public void seedBooks() throws IOException {
        Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                .forEach(row -> {
                    String[] data = row.split("\\s+");

                    Author author = authorService.getRandomAuthor();

                    EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

                    LocalDate releaseDate = LocalDate.parse(data[1],
                            DateTimeFormatter.ofPattern("d/M/yyyy"));


                    int copies = Integer.parseInt(data[2]);

                    BigDecimal price = new BigDecimal(data[3]);

                    AgeRestriction ageRestriction = AgeRestriction
                            .values()[Integer.parseInt(data[4])];

                    String title = Arrays.stream(data)
                            .skip(5)
                            .collect(Collectors.joining(" "));

                    Set<Category> categories = categoryService.getRandomCategories();


                    Book book = new Book(title, editionType, price, copies, ageRestriction, author, releaseDate);
                    book.setCategory(categories);

                    bookService.save(book);
                });
    }
}
