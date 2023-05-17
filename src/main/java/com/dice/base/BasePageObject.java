package com.dice.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePageObject<T> {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Logger log;

    protected BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    protected T getPage(String url) {
        driver.get(url);
        return (T) this;
    }

    protected void type(String text, By element) {
        find(element).sendKeys(text);
    }

    protected void click(By element) {
        find(element).click();
    }

    protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
        int attemps = 0;

        while(attemps < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {

            }
            attemps++;
        }
    }

    protected String getText(By element) {
        return find(element).getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
        wait.until(condition);
    }

    private WebElement find(By element) {
        return driver.findElement(element);
    }
}
