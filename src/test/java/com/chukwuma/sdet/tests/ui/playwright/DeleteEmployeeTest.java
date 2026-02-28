package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.microsoft.playwright.options.LoadState;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeleteEmployeeTest extends BaseTest {

    @Test
    void shouldDeleteEmployeeSuccessfully() {

        String firstName = "FN_" + System.currentTimeMillis();
        String lastName = "LN_" + System.currentTimeMillis();
        String fullName = firstName + " " + lastName;

        LoginPage loginPage = new LoginPage(page);

        String username = ConfigReader.get("APP_USERNAME");
        String password = ConfigReader.get("APP_PASSWORD");
        String baseUrl = ConfigReader.get("BASE_URL");

        page.navigate(baseUrl);

        loginPage.login(username, password);

        EmployeePage employeePage = new EmployeePage(page);

        employeePage.goToPim();
        employeePage.goToAddEmployee();
        employeePage.addEmployee(firstName, lastName);

        employeePage.goToEmployeeList();

        employeePage.searchAndSelectEmployee(fullName);

        employeePage.deleteEmployee(fullName);

        employeePage.searchByNameWithoutSelecting(fullName);

        assertTrue(employeePage.isNoRecordsFoundVisible());
    }
}
