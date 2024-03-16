package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class WordGrid extends WordlePageComponent {
    public WordGrid(WebDriver driver) {
        super(driver);
    }

    public static WordGrid withDriver(WebDriver driver) {
        return new WordGrid(driver);
    }

    public List<String> getCompletedWords() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".completed-row")));
        return driver.findElements(By.cssSelector(".completed-row")).stream()
                .map(row -> row.findElements(By.cssSelector(".letter-container")).stream()
                        .map(WebElement::getText)
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
    }
    /*
    we can write also like these 2 styles the method above:
     1-
    public List<String> getCompletedWords() {
        List<String> words = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".completed-row")));
        List<WebElement> completedLetters
                = driver.findElements(By.cssSelector(".completed-row"));

        for (WebElement letterContainer : completedLetters) {
            List<WebElement> letterContainers = letterContainer.findElements(By.cssSelector(".letter-container"));
            StringBuilder word = new StringBuilder();
            for (WebElement letter : letterContainers) {
                word.append(letter.getText());
            }
            words.add(word.toString());
        }
        2-
        public List<String> getCompletedWords() {
        return driver.findElements(By.cssSelector(".completed-row")).stream()
                .map(row -> row.findElements(By.cssSelector(".letter-container")).stream()
                        .map(WebElement::getText)
                        .reduce(String::concat)
                        .orElse(""))
                        .toList();
    }
     */
    /*
    public boolean isWordPresent(String word) {
        List<String> completedWords = getCompletedWords();
        return completedWords.contains(word);
    }
     */
}

