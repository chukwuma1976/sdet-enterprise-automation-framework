package com.chukwuma.sdet.tests.ui.selenium.pages.locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IdLocatorsPage {
    private final WebDriver driver;
    private final WebElement emailInput;
    private final WebElement passwordInput;
    private final WebElement submitButton;

    public IdLocatorsPage(WebDriver driver) {
        this.driver = driver;
        this.emailInput = driver.findElement(By.id("email"));
        this.passwordInput = driver.findElement(By.id("password"));
        this.submitButton = driver.findElement(By.id("login"));
    }

    public void completeForm(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailInput));

        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        submitButton.submit();
    }

}
