package com.example.usersystem.entities;

import jakarta.persistence.*;

@Entity(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "town_name", nullable = false)
    private String townName;


    @Column(nullable = false)
    private String country;

    public Town() {

    }

    public Town(String townName, String country) {
        this.townName = townName;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
