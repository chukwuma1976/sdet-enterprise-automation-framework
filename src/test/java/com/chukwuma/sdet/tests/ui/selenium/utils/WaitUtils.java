package com.chukwuma.sdet.tests.ui.selenium.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    public static WebElement waitForVisible(WebDriver driver, By locator, int seconds) {

        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForInvisible(WebDriver driver, By locator, int timeout) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator, int timeout) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}