package com.example.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WordlePageComponent {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public WordlePageComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.of(5, ChronoUnit.SECONDS));
    }
}
