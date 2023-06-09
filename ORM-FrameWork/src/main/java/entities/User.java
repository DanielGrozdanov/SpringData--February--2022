package entities;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private int age;

    @Column(name = "registration_date")
    private LocalDate registration;

    @Column(name = "last_logged_in")
    private LocalDate lastLoggedIn;

    public User() {
    }

    public User(String username, int age, LocalDate registration) {
        this.username = username;
        this.age = age;
        this.registration = registration;
        this.lastLoggedIn = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(LocalDate lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", registration=" + registration +
                ", lastLoggedIn=" + lastLoggedIn +
                '}';
    }
}
