package com.example.springdataintroexercise.repositories;

import com.example.springdataintroexercise.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM authors ORDER BY rand() LIMIT 1")
    Author getRandomAuthor();

    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate date);

}
