package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TopBar extends BaseUIInteractions {
    public TopBar(WebDriver driver) {
        super(driver);
    }
    public void newGame() {
        WebElement newGameButton = driver.findElement(By.xpath("//button[.='New Game']"));
        wait.until(ExpectedConditions.elementToBeClickable(newGameButton));
        newGameButton.click();
    }
}
