package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseCrudTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("ui")
@Epic("Employee UI Tests")
@Feature("CRUD operations on Employees")
class AddEmployeeTest extends BaseCrudTest {

    @BeforeEach
    void login() {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    @Test
    @Description("Add a new employee")
    void shouldAddEmployeeSuccessfully() {

        String firstName = TestDataFactory.generateUniqueFirstName();
        String lastName = TestDataFactory.generateUniqueLastName();
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

}
