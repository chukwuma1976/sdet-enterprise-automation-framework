package com.chukwuma.sdet.tests.ui.selenium.pages.locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorsPage {
    private WebDriver driver;
    private By nameInput;
    private By emailinput;
    private By messageInput;
    private By submitButton;
    private By classLocator;
    private By tagName;

    public LocatorsPage(WebDriver driver) {
        this.driver = driver;
        this.nameInput = By.cssSelector("input[type='text']");
        this.emailinput = By.cssSelector("input[type='email']");
        this.messageInput = By.xpath("//textarea[@id='message']");
        this.submitButton = By.cssSelector("input[type='submit']");

        this.classLocator = By.className("codeBlock");
        this.tagName = By.tagName("code");
    }

    public void completeForm(String name, String email, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));

        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailinput).sendKeys(email);
        driver.findElement(messageInput).sendKeys(message);
        driver.findElement(submitButton).submit();
    }

    public void clickLinkByLinkText(String text) {
        driver.findElement(By.linkText(text)).click();
    }

    public void clickLinkByPartialLinkText(String partialText) {
        driver.findElement(By.partialLinkText(partialText)).click();
    }

    public String returnClassNameCodeBlockText() {
        return driver.findElement(classLocator).getText();
    }

    public String returnTagNameCodeText() {
        return driver.findElement(tagName).getText();
    }
}
