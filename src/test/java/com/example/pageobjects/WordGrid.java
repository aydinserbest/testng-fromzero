package com.example.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WordGrid extends WordlePageComponent{
    public WordGrid(WebDriver driver) {
        super(driver);
    }

    public static WordGrid withDriver(WebDriver driver) {
        return new WordGrid(driver);
    }
    /*

    //Should have 1 completed row
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".completed-row")));
        int completedRowCount = driver.findElements(By.cssSelector(".completed-row")).size();
        assertThat(completedRowCount).isEqualTo(1);


    List<String> completedLetters = driver.findElements(By.cssSelector(".completed-row .letter-container"))
                .stream().map(cell -> cell.getText())
                .collect(Collectors.toList());
     */
    public List<String> getCompletedWords() {
        List<String> words = new ArrayList<>();
        List<WebElement> completedWordElements = driver.findElements(By.cssSelector(".completed-row"));
        for (WebElement element : completedWordElements) {
            words.add(element.getText());
        }

        return null;
    }
}

