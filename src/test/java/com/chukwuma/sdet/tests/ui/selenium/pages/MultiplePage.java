package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MultiplePage {
    WebDriver driver;
    String originalWindowHandle;
    String originalTitle;
    String newTitle;

    public MultiplePage(WebDriver driver) {
        this.driver = driver;
        this.originalWindowHandle = driver.getWindowHandle();
    }

    public void verifyOriginalWindowTitle() {
        originalTitle = driver.getTitle();
        assert (originalTitle).equals("Windows page for Automation Testing Practice");
    }

    public void openNewWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement link = driver.findElement(By.cssSelector("a[href='/windows/new']"));
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center'});",
                        link);
        wait.until(ExpectedConditions.elementToBeClickable(link));
        link.click();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindowHandle)) {
                driver.switchTo().window(handle);
                newTitle = driver.getTitle();
                assert (newTitle).equals("Example of a new window");
                break;
            }
        }
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }

}
