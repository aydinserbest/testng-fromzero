package com.example.tests.authentication;

import com.example.model.Player;
import com.example.pageobjects.LoginPage;
import com.example.pageobjects.SignUpPage;
import com.example.pageobjects.ToasterPopUp;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class ValidEmailRules {
    WebDriver driver;
    WebDriverWait wait;
    Player player;

    @BeforeMethod
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1200, 800));
        wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void signUp_with_invalid_email() {
        Player player = Player.somePlayer();

        //Open the login page
        driver.get("http://localhost:5173/");

        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();

        //Register a new user
        SignUpPage.withDriver(driver).signUpAs(player.withEmail("not-an-email"));

        ToasterPopUp toasterPopUp = new ToasterPopUp(driver);
        String errorMessage = toasterPopUp.getMessage();

        assertEquals("Please enter a valid email!", errorMessage);
        //or
        assertThat(errorMessage).contains("Please enter a valid email!");
    }

    @Test(dataProvider = "invalidEmails")
    public void signUp_with_invalid_emails_with_dataProvider(String email, String expectedMessage) {
        Player player = Player.somePlayer().withEmail(email);
        driver.get("http://localhost:5173/");
        //Go to the Create account page
        LoginPage.withDriver(driver).createAccount();
        SignUpPage.withDriver(driver).signUpAs(player);
        ToasterPopUp toasterPopUp = new ToasterPopUp(driver);
        String errorMessage = toasterPopUp.getMessage();
        assertEquals(expectedMessage, errorMessage);
    }
    @DataProvider(name = "invalidEmails")
    public static Object[][] invalidEmails(){
        return new Object[][]{
                {"not-an-email","Please enter a valid email!"},
                {"123456","Please enter a valid email!"},
                {"ab@not-email","Please enter a valid email!"},
        };
    }
}
