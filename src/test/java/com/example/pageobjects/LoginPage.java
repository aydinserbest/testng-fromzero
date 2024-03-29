package com.example.pageobjects;

import com.example.model.Player;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BaseUIInteractions {
    private static final By CREATE_ACCOUNT_BUTTON = By.linkText("Create Account");
    private static final By NAME_FIELD = By.id("name");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void createAccount() {
        driver.findElement(CREATE_ACCOUNT_BUTTON).click();
    }

    public void loginAs(Player player) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
        driver.findElement(NAME_FIELD).sendKeys(player.name());
        driver.findElement(PASSWORD_FIELD).sendKeys(player.password());
        driver.findElement(LOGIN_BUTTON).click();
    }

    public static LoginPage withDriver(WebDriver driver) {
        return new LoginPage(driver);
    }
}
