package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitsDemoPage {
    WebDriver driver;
    By spinner;
    By alert;

    public WaitsDemoPage(WebDriver driver) {
        this.driver = driver;
        this.spinner = By.cssSelector("div.spinner-border");
        this.alert = By.cssSelector("p.alert strong");
    }

    public void fluentlyWaitForAlert() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOfElementLocated(alert));
    }

    public void explicitlyWaitForSpinnerToDisappear() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    public void verifyThatAlertIsDisplayed() {
        assert (driver.findElement(alert)).isDisplayed();
    }

    public void verifyPresenceOfSpinner() {
        assert (driver.findElement(spinner)).isDisplayed();
    }
}
