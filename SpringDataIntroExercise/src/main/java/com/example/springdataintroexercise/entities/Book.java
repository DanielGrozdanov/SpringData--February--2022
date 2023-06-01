package com.example.springdataintroexercise.entities;

import com.example.springdataintroexercise.enums.AgeRestriction;
import com.example.springdataintroexercise.enums.EditionType;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column()
    private String description;

    @Column(nullable = false, name = "edition_type")
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false, name = "age_restriction")
    private AgeRestriction ageRestriction;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany
    private Set<Category> category;

    public Book() {

    }

    public Book(String title, EditionType editionType,
                BigDecimal price, int copies, AgeRestriction ageRestriction, Author author, LocalDate releaseDate) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.copies = copies;
        this.ageRestriction = ageRestriction;
        this.author = author;
        this.releaseDate = releaseDate;
        this.category = new HashSet<>();
    }

    public void addCategory(Category category) {
        this.category.add(category);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategory() {
        return Collections.unmodifiableSet(category);
    }

    public void setCategory(Set<Category> category) {
        this.category = category;
    }
}
