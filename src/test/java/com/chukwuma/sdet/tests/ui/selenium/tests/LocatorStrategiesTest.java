package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.locators.AdvancedLocatorsPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.locators.IdLocatorsPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.locators.LocatorsPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.locators.NameLocatorsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Locator Strategies Tests")
@Feature("Locator Strategies using Selenium")
public class LocatorStrategiesTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Complete form using ID locators")
    @Description("Verify that form can be completed using ID locators")
    void testIdLocator() {
        driver.get(ConfigReader.get("ID_LOCATORS_FORM_PAGE_URL"));

        IdLocatorsPage idLocatorsPage = new IdLocatorsPage(driver);
        idLocatorsPage.completeForm("testIdLocator@example.com", "password123");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Complete form using name locators")
    @Description("Verify that form can be completed using name locators")
    void testNameLocator() {
        driver.get(ConfigReader.get("NAME_LOCATORS_FORM_PAGE_URL"));

        NameLocatorsPage nameLocatorsPage = new NameLocatorsPage(driver);
        nameLocatorsPage.completeForm("nameLocator", "180012345678", "testIdLocator@example.com", "password123");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Complete form using css and xpath locators")
    @Description("Verify that form can be completed using css and xpath locators")
    void testCSSandXpathLocators() {
        driver.get(ConfigReader.get("LOCATORS_FORM_PAGE_URL"));

        LocatorsPage locatorsPage = new LocatorsPage(driver);
        locatorsPage.completeForm("CssandXpath", "testLocators@example.com", "I am testing css and xpath locators");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Retrieve text using class name")
    @Description("Verify that text can be retrieved using class name")
    void testClassNameLocators() {
        driver.get(ConfigReader.get("LOCATORS_FORM_PAGE_URL"));

        LocatorsPage locatorsPage = new LocatorsPage(driver);
        assert (locatorsPage.returnClassNameCodeBlockText()).contains("public class Exercise3");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Retrieve text using tag name")
    @Description("Verify that text can be retrieved using tag name")
    void testTagNameLocators() {
        driver.get(ConfigReader.get("LOCATORS_FORM_PAGE_URL"));

        LocatorsPage locatorsPage = new LocatorsPage(driver);
        assert (locatorsPage.returnTagNameCodeText()).contains("public class Exercise3");
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Find a link by text or partial text")
    @Description("Verify that after finding a link by text that one can navigate")
    void testLinkTextLocators() {
        driver.get(ConfigReader.get("LOCATORS_FORM_PAGE_URL"));

        LocatorsPage locatorsPage = new LocatorsPage(driver);

        locatorsPage.clickLinkByLinkText("Corporate Training");
        assert (driver.getCurrentUrl()).contains("corporate");
        driver.navigate().back();

        locatorsPage.clickLinkByPartialLinkText("Academics");
        assert (driver.getCurrentUrl()).contains("academic");

    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("CSS locator strategies")
    @Description("Test a variety of CSS locator strategies")
    void testCSSLocators() {
        driver.get(ConfigReader.get("ADVANCED_LOCATORS_URL"));
        AdvancedLocatorsPage locatorsPage = new AdvancedLocatorsPage(driver);
        locatorsPage.confirmCssSelectors();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Xpath locator strategies")
    @Description("Test a variety of Xpath locator strategies")
    void testXpathLocators() {
        driver.get(ConfigReader.get("ADVANCED_LOCATORS_URL"));
        AdvancedLocatorsPage locatorsPage = new AdvancedLocatorsPage(driver);
        locatorsPage.confirmXpathSelectors();
    }
}
