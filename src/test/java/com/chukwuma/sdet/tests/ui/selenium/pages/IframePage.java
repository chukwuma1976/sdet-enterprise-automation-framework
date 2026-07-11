package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IframePage {
    WebDriver driver;
    By youTubeIframe = By.id("iframe-youtube");
    By playButton = By.cssSelector("button[aria-label=\"Play video\"]");
    By tinyMceIframe = By.id("mce_0_ifr");
    By tinyMceEditor = By.id("tinymce");
    By subscribeIframe = By.id("email-subscribe");
    By emailInput = By.id("email");
    By subscribeBtn = By.id("btn-subscribe");

    public IframePage(WebDriver driver) {
        this.driver = driver;
    }

    public void handleYouTubeIframe() {
        WebElement iframe = driver.findElement(youTubeIframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.switchTo().frame(iframe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(playButton));
        WebElement button = driver.findElement(playButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
        driver.switchTo().defaultContent();
    }

    public void handleTinyMceIframe() {
        WebElement iframe = driver.findElement(tinyMceIframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.switchTo().frame(iframe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(tinyMceEditor));
        assert (driver.findElement(tinyMceEditor).getText()).equals("Your content goes here.");
        driver.switchTo().defaultContent();
    }

    public void handleSubscriptionIframe() {
        WebElement iframe = driver.findElement(subscribeIframe);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.switchTo().frame(iframe);

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys("selenium.automation@fakemail.com");
        WebElement button = driver.findElement(subscribeBtn);
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        button);
        wait.until(ExpectedConditions.elementToBeClickable(subscribeBtn));
        button.click();
        driver.switchTo().defaultContent();
    }
}
