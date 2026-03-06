package com.chukwuma.sdet.tests.ui.selenium.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.DashboardPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.LoginPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.PimPage;
import com.chukwuma.sdet.utils.TestDataFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("smoke")
@Tag("ui")
@Epic("Selenium Employee CRUD Tests")
@Feature("Add Employee")
public class AddNewEmployeeTest extends BaseTest {

    @Test
    @DisplayName("User can add and then new employee")
    @Description("Add and Delete a new employee")
    void shouldAddNewEmployeeSuccessfully() {
        String firstName = TestDataFactory.generateUniqueFirstName();
        String lastName = TestDataFactory.generateUniqueLastName();
        String employeeId = TestDataFactory.generateEmployeeId();

        driver.get(ConfigReader.get("BASE_URL"));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("APP_USERNAME"), ConfigReader.get("APP_PASSWORD"));

        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.selectPIM();

        PimPage pimPage = new PimPage(driver);
        pimPage.clickAddEmployee();
        pimPage.addEmployee(firstName, lastName, employeeId);
        assertTrue(pimPage.isEmployeeAdded(firstName, lastName));

        pimPage.clickEmployeeList();
        pimPage.searchEmployee(employeeId);
        pimPage.deleteEmployee(employeeId);
    }

}
