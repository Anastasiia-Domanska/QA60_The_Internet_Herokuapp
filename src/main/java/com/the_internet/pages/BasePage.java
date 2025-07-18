package com.the_internet.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if(text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }

    }

    public void pause(int milies) {
        try {
            Thread.sleep(milies);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void moveWithJS(int x, int y) {

        js.executeScript("window.scrollBy(" + x + ", " + y + ")");
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementDisplayed(WebElement element){
        try {
            element.isDisplayed();
            return true;
        }catch (NoSuchElementException exception){
            exception.getMessage();
            return false;
        }
    }
}
