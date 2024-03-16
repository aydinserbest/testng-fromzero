package com.example.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OnScreenKeyboard extends WordlePageComponent {

    public OnScreenKeyboard(WebDriver driver) {
        super(driver);
    }
    public void press(String key) {
        try {
            WebElement keyboard = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label*='" + key.toUpperCase() + "'], button[aria-label*='" + key.toUpperCase() + " absent']")));
            keyboard.click();
        } catch (Exception e) {
            // Element tıklanabilir değilse veya bulunamıyorsa, JavaScript ile tıklamayı dene.
            List<WebElement> elements = driver.findElements(By.cssSelector("button[aria-label*='" + key.toUpperCase() + "'], button[aria-label*='" + key.toUpperCase() + " absent']"));
            if (!elements.isEmpty()) {
                WebElement element = elements.get(0); // Eşleşen ilk elementi al
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                throw new NoSuchElementException("Key not found or not clickable: " + key);
            }
        }
    }


}
