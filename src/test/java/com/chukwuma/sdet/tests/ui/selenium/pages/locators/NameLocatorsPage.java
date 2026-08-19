package com.chukwuma.sdet.tests.ui.selenium.pages.locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NameLocatorsPage {
    private final WebDriver driver;
    private final WebElement nameInput;
    private final WebElement mobileInput;
    private final WebElement emailInput;
    private final WebElement passwordInput;
    private final WebElement submitButton;

    public NameLocatorsPage(WebDriver driver) {
        this.driver = driver;
        this.nameInput = driver.findElement(By.name("name"));
        this.mobileInput = driver.findElement(By.name("mobile"));
        this.emailInput = driver.findElement(By.name("email"));
        this.passwordInput = driver.findElement(By.name("password"));
        this.submitButton = driver.findElement(By.name("submit"));
    }

    public void completeForm(String name, String mobile, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(mobileInput));

        nameInput.sendKeys(name);
        mobileInput.sendKeys(mobile);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);

        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("arguments[0].click();", submitButton);
        submitButton.submit();
    }

}
