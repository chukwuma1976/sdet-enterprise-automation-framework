package com.chukwuma.sdet.tests.ui.selenium.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementsFormPage {
    private final WebDriver driver;

    public WebElementsFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInForm() {

        selectOptionWithValue("prefix", "mr.");
        inputTextValue("firstname", "Kal");
        inputTextValue("lastname", "El");
        clearInputTextValue("firstname");
        clearInputTextValue("lastname");
        inputTextValue("firstname", "Clark");
        inputTextValue("lastname", "Kent");
        checkInputValue("firstname", "Clark");
        checkInputValue("lastname", "Kent");

        driver.findElement(By.id("saving")).click();
        assert (driver.findElement(By.id("saving"))).isSelected();

        inputTextValue("fathername", "Jonathan Kent");
        inputTextValue("mothername", "Martha Kent");

        driver.findElement(By.id("passport")).click();
        inputTextValue("identity_number", "616");

        WebElement maleRadioButton = driver.findElement(By.id("male"));
        if (!maleRadioButton.isSelected())
            maleRadioButton.click();

        selectOptionWithText("dob_month", "December");
        selectOptionWithText("dob_date", "25");
        selectOptionWithText("dob_year", "1993");

        driver.findElement(By.id("married")).click();
        selectOptionWithValue("country_code", "1");

        inputTextValue("mobile", "1-800-616-0000");

        selectOptionWithText("nationality", "American");

        inputTextValue("address", "Metropolis");
        inputTextValue("state", "New York");

        selectOptionWithText("country", "United States");

        driver.findElement(By.cssSelector("input[type='submit']")).submit();
    }

    private void selectOptionWithValue(String id, String value) {
        Select select = new Select(driver.findElement(By.id(id)));
        select.selectByValue(value);
    }

    private void selectOptionWithText(String id, String value) {
        Select select = new Select(driver.findElement(By.id(id)));
        select.selectByVisibleText(value);
    }

    private void inputTextValue(String id, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        driver.findElement(By.id(id)).sendKeys(value);
    }

    private void clearInputTextValue(String id) {
        driver.findElement(By.id(id)).clear();
    }

    private void checkInputValue(String id, String value) {
        assert (driver.findElement(By.id(id)).getAttribute("value")).equals(value);
    }

}
