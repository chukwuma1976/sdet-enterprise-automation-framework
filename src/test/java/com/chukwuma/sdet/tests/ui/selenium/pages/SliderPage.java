package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SliderPage {
    private WebDriver driver;
    private WebElement slider;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        this.slider = driver.findElement(By.cssSelector("div.sliderContainer input"));
    }

    public void slideElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(slider));

        Actions action = new Actions(driver);
        action.clickAndHold(slider).moveByOffset(100, 0).release().perform();
        assert (driver.findElement(By.id("range")).getText()).equals("5");
    }
}
