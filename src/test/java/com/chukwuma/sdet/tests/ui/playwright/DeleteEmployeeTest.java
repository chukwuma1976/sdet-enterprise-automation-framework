package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

class DeleteEmployeeTest extends BaseTest {

    @BeforeEach
    void login() {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    @Test
    void shouldDeleteEmployeeSuccessfully() {

        String firstName = "FN_" + System.currentTimeMillis();
        String lastName = "LN_" + System.currentTimeMillis();
        String employeeId = TestDataFactory.generateEmployeeId();

        EmployeePage employeePage = new EmployeePage(page);

        employeePage.goToPim();
        employeePage.goToAddEmployee();
        employeePage.addEmployee(firstName, lastName, employeeId);

        employeePage.goToEmployeeList();
        employeePage.searchByEmployeeIdAndSelect(employeeId);

        employeePage.deleteEmployee(employeeId);
        employeePage.searchByEmployeeIdAndSelect(employeeId);

        assertTrue(employeePage.isNoRecordsFoundVisible());
    }
}
