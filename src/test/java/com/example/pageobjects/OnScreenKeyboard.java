package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OnScreenKeyboard extends WordlePageComponent{

    public OnScreenKeyboard(WebDriver driver) {
        super(driver);
    }

    public void press(String key) {
        WebElement keyboard = driver.findElement(By.cssSelector("button[aria-label='" + key.toUpperCase() + "']"));
        keyboard.click();

    }
}
