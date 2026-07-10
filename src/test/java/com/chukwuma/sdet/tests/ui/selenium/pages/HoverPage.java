package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HoverPage {
    private WebDriver driver;
    private By image1 = By.cssSelector("img[data-testid='img-user-1']");
    private By image2 = By.cssSelector("img[data-testid='img-user-2']");
    private By image3 = By.cssSelector("img[data-testid='img-user-3']");
    private By caption1 = By.xpath("//h5[contains(text(), 'user1')]");
    private By caption2 = By.xpath("//h5[contains(text(), 'user2')]");
    private By caption3 = By.xpath("//h5[contains(text(), 'user3')]");

    public HoverPage(WebDriver driver) {
        this.driver = driver;
    }

    public void hoverOverElements() {
        hoverOverImage(image1, caption1);
        hoverOverImage(image2, caption2);
        hoverOverImage(image3, caption3);
    }

    private void hoverOverImage(By image, By caption) {
        WebElement element = driver.findElement(image);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(element));

        Actions action = new Actions(driver);
        action.scrollToElement(driver.findElement(By.tagName("footer"))).perform();
        action.moveToElement(element).perform();
        assert (driver.findElement(caption)).isDisplayed();
    };

}
