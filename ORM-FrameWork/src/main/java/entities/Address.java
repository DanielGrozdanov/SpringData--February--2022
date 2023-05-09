package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

@Entity(name = "addresses")
public class Address {

    @Id
    private int id;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "street")
    private String street;

    @Column(name = "street_number")
    private int streetNumber;

    public Address() {

    }


    public Address(String city, String country, String postalCode, String street, int streetNumber) {
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
}
