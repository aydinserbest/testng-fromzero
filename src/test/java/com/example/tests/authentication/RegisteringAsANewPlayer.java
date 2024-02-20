package com.example.tests.authentication;

import com.example.model.Player;
import com.example.pageobjects.HowToPlayModal;
import com.example.pageobjects.LoginPage;
import com.example.pageobjects.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisteringAsANewPlayer {
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setupDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 800));
        wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
    }
    @AfterMethod
    public void closeDriver(){
        driver.quit();
    }
    @Test
    public void aNewPlayerSignsUp(){
        Player player = Player.somePlayer();

        //Open the login page
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(player);

        //Try to login as this user
        LoginPage.withDriver(driver).loginAs(player);

        //We should go to the game page
        assertThat(HowToPlayModal.withDriver(driver).getTitle()).isEqualTo("How to play");

    }
}
