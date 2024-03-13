package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ToasterPopUp extends WordlePageComponent{

    public static final By TOAST_CLOSE_BUTTON = By.cssSelector(".Toastify button");
    public static final By TOASTER_POPUP = By.xpath("//div[contains(@class, 'Toastify__toast--error')]");

    public ToasterPopUp(WebDriver driver) {
        super(driver);
    }
    public String getMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_CLOSE_BUTTON));
        return driver.findElement(TOASTER_POPUP).getText();
    }
}
