package com.example.tests.authentication;

import com.example.model.Player;
import com.example.pageobjects.HowToPlayModal;
import com.example.pageobjects.LoginPage;
import com.example.pageobjects.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    @Test(description = "A new player should be able to sign up by providing a name, a password and an email")
    public void aNewPlayerSignsUp() {
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
    @Test
    public void signUp_with_invalid_email(){
        Player player = Player.playerWithInvalidEmail();

        //Open the login page
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(player);

        // Find the Toastify error message element
        WebElement toastError = driver.findElement(By.xpath("//div[contains(@class, 'Toastify__toast--error')]"));

        // Check if the Toastify error message is displayed
        Boolean isErrorDisplayed = wait.until((ExpectedCondition<Boolean>) driver -> {
            WebElement toastError1 = driver.findElement(By.xpath("//div[contains(@class, 'Toastify__toast--error')]"));
            return toastError1 != null && toastError1.isDisplayed();
        });

        // Get the text from the error message and assert its content if it's displayed
        String errorMessage = toastError.getText();
        Assert.assertEquals("Please enter a valid email!", errorMessage);
    }
    @Test
    public void with_bad_password(){
        Player player=Player.playerWithShortPassword();
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        // Attempt to sign up with a player having a short password
        SignUpPage.withDriver(driver).signUpAs(player, false);

        // Locate the password validation message container
        WebElement passwordValidationDiv = driver.findElement(By.cssSelector("div[class*='rounded-full'][class*='bg-red-200']"));

        // Retrieve the class attribute value of the validation message
        String classValue = passwordValidationDiv.getAttribute("class");

        // In case of a short password, the 'passwordValidationDiv' should contain 'text-red-700', indicating an error
        // When the password is adequate, it should contain 'text-green-700', indicating success
        // We use this information for assertion
        assertThat(classValue).contains("text-red-700");

        //diger bir assert yontemi ise,
        //
        String text = driver.findElement(By.cssSelector(".text-sm.font-medium.text-red-700")).getText();
        assertThat(text).isEqualTo("At least 8 characters required");


    }
}
