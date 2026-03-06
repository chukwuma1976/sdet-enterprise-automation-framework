package com.chukwuma.sdet.tests.ui.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.chukwuma.sdet.tests.ui.selenium.utils.ElementActions;
import com.chukwuma.sdet.tests.ui.selenium.utils.WaitUtils;

public class LoginPage {

    private WebDriver driver;

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By dashBoardHeader = By.xpath("//h6[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {

        ElementActions.type(driver, username, user);
        ElementActions.type(driver, password, pass);

        driver.findElement(loginBtn).click();
        WaitUtils.waitForVisible(driver, dashBoardHeader, 30);
    }

}
