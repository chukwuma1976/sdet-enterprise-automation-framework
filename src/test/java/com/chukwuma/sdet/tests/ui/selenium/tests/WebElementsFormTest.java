package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.WebElementsFormPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Web Elements Command")
@Feature("Form with a multitude of web elements")
public class WebElementsFormTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("User can use all form elements on the page")
    @Description("Verify that all form elements are present and work")
    void shouldVerifyThatFormElementsWork() {

        driver.get(ConfigReader.get("FORM_LOCATORS_PRACTICE_URL"));

        WebElementsFormPage form = new WebElementsFormPage(driver);
        form.fillInForm();

    }

}
