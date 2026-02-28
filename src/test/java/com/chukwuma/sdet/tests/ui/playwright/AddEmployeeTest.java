package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;

class AddEmployeeTest extends BaseTest {

    @Test
    void shouldAddEmployeeSuccessfully() {

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

        assertTrue(
                employeePage.isEmployeePresent(fullName),
                "Expected employee " + fullName + " to appear in search results");

        // Temp: Cleanup - Delete the employee we just created
        employeePage.goToEmployeeList();
        employeePage.searchAndSelectEmployee(fullName);
        employeePage.deleteEmployee(fullName);
    }
}
