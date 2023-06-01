package com.example.usersystem;

import com.example.usersystem.entities.Town;
import com.example.usersystem.entities.User;
import com.example.usersystem.services.TownService;
import com.example.usersystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleLineRunner implements CommandLineRunner {


    private UserService userService;
    private TownService townService;

    @Autowired
    public ConsoleLineRunner(UserService userService, TownService townService) {
        this.userService = userService;
        this.townService = townService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Town town = new Town("Samokov", "Bulgaria");
        User user = new User("Void", "Daniel", "Fox", "12345", "daniel.grozdanov@gmail.com", town, town);
        user.setFullName();

        this.townService.saveTown(town);
        this.userService.saveUser(user);


    }
}
