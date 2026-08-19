package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DragAndDropCirclesPage {
    private WebDriver driver;
    private WebElement targetContainer;

    public DragAndDropCirclesPage(WebDriver driver) {
        this.driver = driver;
        this.targetContainer = driver.findElement(By.id("target"));
    }

    public void dragAndDropCircles() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(targetContainer));

        WebElement redCircle = driver.findElement(By.cssSelector("div.red"));
        WebElement greenCircle = driver.findElement(By.cssSelector("div.green"));
        WebElement blueCircle = driver.findElement(By.cssSelector("div.blue"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(redCircle, targetContainer).perform();
        actions.dragAndDrop(greenCircle, targetContainer).perform();
        actions.dragAndDrop(blueCircle, targetContainer).perform();
    }

    public void confirmCirclesAreInTarget() {
        assert (targetContainer.findElement(By.cssSelector("div.red"))).isDisplayed();
        assert (targetContainer.findElement(By.cssSelector("div.green"))).isDisplayed();
        assert (targetContainer.findElement(By.cssSelector("div.blue"))).isDisplayed();
    }
}
