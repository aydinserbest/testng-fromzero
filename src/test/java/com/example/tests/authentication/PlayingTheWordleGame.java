package com.example.tests.authentication;

import com.example.model.Player;
import com.example.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayingTheWordleGame {
    WebDriver driver;
    WebDriverWait wait;
    Player player;

    @BeforeClass
    void registerAndStartTheGame(){
        driver = WebDriverProvider.getDriver();
        wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));

        player = Player.somePlayer();

        //Open the login page
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(player);

        //Try to login as this user
        LoginPage.withDriver(driver).loginAs(player);

        //We should get to the game page
        HowToPlayModal howToPlayModal = new HowToPlayModal(driver);
        howToPlayModal.closeModal();
    }

    @AfterMethod
    public void closeDriver() {
        WebDriverProvider.quitDriver();
    }
    @Test
    public void shouldBeAbleToEnterAWordUsingTheKeyboard(){
        OnScreenKeyboard onScreenKeyboard = new OnScreenKeyboard(driver);
//        onScreenKeyboard.press("B");
//        onScreenKeyboard.press("E");
//        onScreenKeyboard.press("A");
//        onScreenKeyboard.press("S");
//        onScreenKeyboard.press("T");
//        onScreenKeyboard.press("enter");
        onScreenKeyboard.press("A");
        onScreenKeyboard.press("N");
        onScreenKeyboard.press("G");
        onScreenKeyboard.press("L");
        onScreenKeyboard.press("E");
        onScreenKeyboard.press("enter");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(1).containsExactly("BEAST");

    }
    @Test
    public void shouldBeAbleToEnterMultipleWordsUsingTheKeyboard(){
        OnScreenKeyboard onScreenKeyboard = new OnScreenKeyboard(driver);
        onScreenKeyboard.press("B");
        onScreenKeyboard.press("E");
        onScreenKeyboard.press("A");
        onScreenKeyboard.press("S");
        onScreenKeyboard.press("T");
        onScreenKeyboard.press("enter");
        onScreenKeyboard.press("A");
        onScreenKeyboard.press("N");
        onScreenKeyboard.press("G");
        onScreenKeyboard.press("L");
        onScreenKeyboard.press("E");
        onScreenKeyboard.press("enter");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(2).containsExactly("BEAST", "ANGLE");
    }
    @Test
    public void shouldBeAbleToEnterNUsingTheKeyboard(){
        OnScreenKeyboard onScreenKeyboard = new OnScreenKeyboard(driver);
        onScreenKeyboard.press("A");
        onScreenKeyboard.press("N");
        onScreenKeyboard.press("G");
        onScreenKeyboard.press("E");
        onScreenKeyboard.press("L");
        onScreenKeyboard.press("enter");
        onScreenKeyboard.press("S");
        onScreenKeyboard.press("A");
        onScreenKeyboard.press("I");
        onScreenKeyboard.press("N");
        onScreenKeyboard.press("T");
        onScreenKeyboard.press("enter");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(2).containsExactly("BEAST", "ANGLE");
    }
}
