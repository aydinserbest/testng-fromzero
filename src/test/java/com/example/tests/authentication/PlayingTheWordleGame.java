package com.example.tests.authentication;

import com.example.actions.GameActions;
import com.example.model.Player;
import com.example.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

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

    @AfterClass
    public void closeDriver() {
        WebDriverProvider.quitDriver();
    }
    @BeforeMethod
    void startTheGame(){
        ToasterPopUp toasterPopUp = new ToasterPopUp(driver);
        toasterPopUp.closeIfVisible();

        TopBar topBar = new TopBar(driver);
        topBar.newGame();
    }

    @Test
    public void shouldBeAbleToEnterAWordUsingTheKeyboard(){
        GameActions gameActions = new GameActions(driver);
        gameActions.playWord("ANGEL");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(1).containsExactly("ANGEL");

    }
    @Test
    public void shouldBeAbleToEnterMultipleWordsUsingTheKeyboard(){
        GameActions gameActions = new GameActions(driver);
        gameActions.playWord("BEAST");
        gameActions.playWord("ANGEL");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(2).containsExactly("BEAST", "ANGEL");
    }
    @Test
    public void shouldBeAbleToEnterMultipleWordsUsingTheKeyboard2(){
        GameActions gameActions = new GameActions(driver);
        gameActions.playWords("BEAST","ANGEL","SAINT");

        List<String> words = WordGrid.withDriver(driver).getCompletedWords();
        assertThat(words).hasSize(3).containsExactly("BEAST", "ANGEL", "SAINT");
    }
}
