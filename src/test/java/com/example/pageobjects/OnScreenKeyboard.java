package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OnScreenKeyboard extends BaseUIInteractions {

    public OnScreenKeyboard(WebDriver driver) {
        super(driver);
    }
    public void press(String key) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[aria-label^='" + key.toUpperCase() + "']")));
        WebElement keyboard = driver.findElement(By.cssSelector("button[aria-label^='" + key.toUpperCase() + "']"));
        keyboard.click();
    }
    }

