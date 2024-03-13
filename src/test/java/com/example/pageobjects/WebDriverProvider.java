package com.example.pageobjects;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverProvider {
    private static ThreadLocal<WebDriver> THREADLOCAL_WEBDRIVER = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (THREADLOCAL_WEBDRIVER.get() == null) {
            WebDriver driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1200, 800));
            THREADLOCAL_WEBDRIVER.set(driver);
        }
        return THREADLOCAL_WEBDRIVER.get();
    }
    public static void quitDriver(){
        if (THREADLOCAL_WEBDRIVER.get() == null) {
            THREADLOCAL_WEBDRIVER.get().quit();
            THREADLOCAL_WEBDRIVER.remove();
        }
    }
}
