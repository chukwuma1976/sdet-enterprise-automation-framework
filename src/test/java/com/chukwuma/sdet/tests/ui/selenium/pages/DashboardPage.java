package com.chukwuma.sdet.tests.ui.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.chukwuma.sdet.tests.ui.selenium.utils.WaitUtils;

public class DashboardPage {

    private WebDriver driver;

    private By pimButton = By.cssSelector("a[href='/web/index.php/pim/viewPimModule']");
    private By pimHeader = By.xpath("//h6[text()='PIM']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        waitForPageReady();
    }

    public void selectPIM() {

        WaitUtils.waitForClickable(driver, pimButton, 30);
        driver.findElement(pimButton).click();

        // wait for PIM page to load
        WaitUtils.waitForVisible(driver, pimHeader, 30);
    }

    private void waitForPageReady() {
        By loader = By.cssSelector(".oxd-form-loader");
        WaitUtils.waitForInvisible(driver, loader, 30);
    }

}
