package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class HowToPlayModal extends WordlePageComponent{
    private static final By MODAL_TITLE = By.cssSelector(".modal-title");
    //private static final By MODAL_CLOSE = By.xpath("//button[@class='absolute right-4 top-4']//*[name()='svg']");
    private static final By MODAL_CLOSE = By.cssSelector("button.absolute.right-4.top-4 svg.h-6.w-6.cursor-pointer.dark\\:stroke-white");

    public HowToPlayModal(WebDriver driver) {
        super(driver);
     }

    public static HowToPlayModal withDriver(WebDriver driver) {
        return new HowToPlayModal(driver);
    }

    public String getTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_TITLE));
        return driver.findElement(MODAL_TITLE).getText();
    }

    public void closeModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(MODAL_CLOSE));
        driver.findElement(MODAL_CLOSE).click();
    }
}
/*
MODAL_CLOSE element location:


 */
