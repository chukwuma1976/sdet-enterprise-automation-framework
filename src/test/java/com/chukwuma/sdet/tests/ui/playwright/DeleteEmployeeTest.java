package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

@Epic("Employee UI Tests")
@Feature("CRUD operations on Employees")
class DeleteEmployeeTest extends BaseTest {

    @BeforeEach
    void login() {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    @Test
    @Description("Delete an existing employee")
    void shouldDeleteEmployeeSuccessfully() {

        String firstName = TestDataFactory.generateUniqueFirstName();
        String lastName = TestDataFactory.generateUniqueLastName();
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
