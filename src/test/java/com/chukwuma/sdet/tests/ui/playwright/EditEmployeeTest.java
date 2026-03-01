package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseCrudTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.LoginPage;
import com.chukwuma.sdet.utils.TestDataFactory;

public class EditEmployeeTest extends BaseCrudTest {

    @BeforeEach
    void login() {
        page.navigate(ConfigReader.get("BASE_URL"));
        new LoginPage(page).login(
                ConfigReader.get("APP_USERNAME"),
                ConfigReader.get("APP_PASSWORD"));
    }

    @Test
    void shouldEditEmployeeSuccessfully() {

        String firstName = TestDataFactory.generateUniqueFirstName();
        String middleName = TestDataFactory.generateUniqueMiddleName(); // New middle name for editing
        String lastName = TestDataFactory.generateUniqueLastName();
        employeeId = TestDataFactory.generateEmployeeId();

        EmployeePage employeePage = new EmployeePage(page);

        employeePage.goToPim();
        employeePage.goToAddEmployee();
        employeePage.addEmployee(firstName, lastName, employeeId);

        employeePage.goToEmployeeList();
        employeePage.searchByEmployeeIdAndSelect(employeeId);
        employeePage.editEmployee(employeeId);

        employeePage.addMiddleNameAndSave(middleName); // Add middle name to edit the employee

        assertTrue(
                employeePage.isMiddleNamePersisted(middleName),
                "Expected middle name to be saved correctly");
    }
}
