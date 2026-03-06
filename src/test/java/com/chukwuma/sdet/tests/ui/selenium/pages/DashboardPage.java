package com.chukwuma.sdet.tests.ui.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.chukwuma.sdet.tests.ui.selenium.utils.WaitUtils;

public class DashboardPage {

    private WebDriver driver;

    private By pimButton = By.cssSelector("a[href='/web/index.php/pim/viewPimModule']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPIM() {
        WaitUtils.waitForVisible(driver, pimButton, 10);
        driver.findElement(pimButton).click();
    }

}
