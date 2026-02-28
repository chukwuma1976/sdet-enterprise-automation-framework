package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataFactory;

class AddEmployeeTest extends BaseTest {

    String employeeId = TestDataFactory.generateEmployeeId();

    @BeforeEach
    void login() {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    @Test
    void shouldAddEmployeeSuccessfully() {

        String firstName = "FN_" + System.currentTimeMillis();
        String lastName = "LN_" + System.currentTimeMillis();
        String fullName = firstName + " " + lastName;
        employeeId = TestDataFactory.generateEmployeeId();

        EmployeePage employeePage = new EmployeePage(page);

        employeePage.goToPim();
        employeePage.goToAddEmployee();
        employeePage.addEmployee(firstName, lastName, employeeId);

        employeePage.goToEmployeeList();
        employeePage.searchAndSelectEmployee(fullName);

        assertTrue(
                employeePage.isEmployeePresent(fullName),
                "Expected employee " + fullName + " to appear in search results");
    }

    @AfterEach
    void cleanup() {
        if (employeeId != null) {
            try {
                EmployeePage employeePage = new EmployeePage(page);
                employeePage.goToEmployeeList();
                employeePage.searchByEmployeeIdAndSelect(employeeId);
                employeePage.deleteEmployee(employeeId);
            } catch (Exception e) {
                System.out.println("Cleanup failed for employeeId: " + employeeId);
            }
        }
    }
}
