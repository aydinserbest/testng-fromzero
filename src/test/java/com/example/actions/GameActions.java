package com.example.actions;

import com.example.pageobjects.BaseUIInteractions;
import com.example.pageobjects.OnScreenKeyboard;
import org.openqa.selenium.WebDriver;

public class GameActions extends BaseUIInteractions {
    public GameActions(WebDriver driver) {
        super(driver);
    }

    public void playWord(String word) {
        OnScreenKeyboard onScreenKeyboard = new OnScreenKeyboard(driver);
        for (char c : word.toCharArray()){
            onScreenKeyboard.press(String.valueOf(c));
        }
        onScreenKeyboard.press("enter");
    }

    public void playWords(String... wordList) {
        for (String word : wordList) {
            playWord(word);
        }
    }
}
