package com.example.model;

import com.github.javafaker.Faker;

public record Player (String name, String email, String password){
    public static Player somePlayer() {
        return new Player(
                Faker.instance().name().username(),
                Faker.instance().internet().emailAddress(),
                "TOPs3cre&t"
        );
    }
    public static Player playerWithInvalidEmail() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                "invalid-email",
                "TOPs3cre&t"
        );
    }
    public static Player playerWithShortPassword() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                validPlayer.email(),
                "Freee1*"
        );
    }
    public static Player playerWithPasswordMissingUppercase() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                validPlayer.email(),
                "freeee1*"
        );
    }
    public static Player playerWithPasswordMissingLowercase() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                validPlayer.email(),
                "FREEEE1*"
        );
    }
    public static Player playerWithMissingSpecialCharacter() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                validPlayer.email(),
                "Freeeee1"
        );
    }
    public static Player playerWithMissingOneNumber() {
        Player validPlayer = somePlayer();
        return new Player(
                validPlayer.name(),
                validPlayer.email(),
                "Freeeee*"
        );
    }

}
