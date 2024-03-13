package com.example.tests.authentication;

import com.example.model.Player;
import com.example.pageobjects.HowToPlayModal;
import com.example.pageobjects.LoginPage;
import com.example.pageobjects.SignUpPage;
import com.example.pageobjects.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class RegisteringAsANewPlayer {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setupDriver() {
        driver = WebDriverProvider.getDriver();
        wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
    }

    @AfterMethod
    public void closeDriver() {
        WebDriverProvider.quitDriver();
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
    public void with_bad_password() {
        Player player = Player.somePlayer();
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        // Attempt to sign up with a player having a short password
        SignUpPage.withDriver(driver).signUpAs(player.withPassword("Ay.3*aa"));

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
    @Test
    public void with_bad_password2() {
        //to create Player object in one line (in one step)
        Player invalidPlayer = Player.somePlayer().withPassword("Ay.3*aa");
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        // Attempt to sign up with a player having a short password
        SignUpPage.withDriver(driver).signUpAs(invalidPlayer, false);

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

    @DataProvider(name = "invalidPasswords")
    public static Object[][] invalidPasswords() {
        return new Object[][]{
                {"Short1@", "At least 8 characters required"},
                {"invalid1@", "Password should contain with one uppercase"},
                {"INVALID1@", "Password should contain with one lowercase"},
                {"Invalid1", "Password should contain with one special character"},
                {"Invalid@", "Password should contain with one number"}
        };
    }

    @Test(dataProvider = "invalidPasswords")
    public void testInvalidPassword(String password, String expectedMessage) {
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(new Player("testuser", "testemail@example.com", password),false);
        WebElement validationMessageElement = driver.findElement(By.cssSelector("span.text-red-700"));
        String actualMessage = validationMessageElement.getText();
        // Compare the actual validation message to the expected one from the data provider
        assertEquals(actualMessage, expectedMessage, "The password validation message is incorrect.");
    }

    @Test(dataProvider = "invalidPasswords")
    public void testInvalidPassword2(String password, String expectedMessage) {
        Player player = Player.somePlayer();
        Player playerWithInvalidPassword = player.withPassword(password); // coming from dataProvider
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(playerWithInvalidPassword);
        WebElement validationMessageElement = driver.findElement(By.cssSelector("span.text-red-700"));
        String actualMessage = validationMessageElement.getText();
        // Compare the actual validation message to the expected one from the data provider
        assertEquals(actualMessage, expectedMessage, "The password validation message is incorrect.");

    }
}
