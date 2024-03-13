package com.example.model;

import com.github.javafaker.Faker;

public record Player(String name, String email, String password) {
    /*\
    In this example, Player is a class declared as a record,
    a feature introduced in Java 14.
    This is used to define what are often called "data carrier" or "value" objects
    and automatically generates more tedious methods such as equals, hashCode, toString, getters, and setters.
    This means that all data of the class is openly and directly accessible and is immutable.
    The Player class here has fields name, email,password and defines two methods, somePlayer() and withEmail(String email).
    The somePlayer() method is a static method that creates and returns an instance of the Player.
    The Faker.instance().name().username() and Faker.instance().internet().emailAddress() methods
    generate a random name and email address.
    "TOPs3cre&t" is a fixed string used as the password.
    The withEmail(String email) method, on the other hand,
    is an instance method that returns a new Player instance which is a copy of the current one
    having the same name and password values but with the email replaced by the argument provided to the withEmail method.
    This works like a "copy constructor" and is often used in "immutable" objects. Here is how it used:
        Player player1 = Player.somePlayer(); // Creates Player with random name and email
        Player player2 = player1.withEmail("new.email@example.com"); // Creates a copy of player1, but with a different email

    player1 and player2 have the same name and password,
    but different emails with player2 having the email "new.email@example.com" which is the argument value to withEmail call.
    In short, withEmail method takes one instance of this class, change the email attribute and creates a new Player object.
    In this way, the original Player object is not modified,
    which reduces complexity and prevents bug-prone states.
     */
    public static Player somePlayer() {
        return new Player(
                Faker.instance().name().username(),
                Faker.instance().internet().emailAddress(),
                "TOPs3cre&t"
        );
    }

    public Player withEmail(String email) {
        return new Player(name, email, password);
    }
    public Player withPassword(String password) {
        return new Player(name, email, password);
    }
}
