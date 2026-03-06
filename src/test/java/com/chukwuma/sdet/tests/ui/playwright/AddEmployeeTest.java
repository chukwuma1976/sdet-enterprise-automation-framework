package com.chukwuma.sdet.tests.ui.playwright;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.chukwuma.sdet.base.BaseCrudTest;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.utils.TestDataFactory;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("playwright")
@Tag("smoke")
@Tag("ui")
@Epic("Employee UI Tests")
@Feature("CRUD operations on Employees")
class AddEmployeeTest extends BaseCrudTest {

    @BeforeEach
    void login() {
        new AuthHelper(page).loginAsDefaultUser();
    }

    @Test
    @DisplayName("User can add a new employee")
    @Description("Add a new employee")
    void shouldAddEmployeeSuccessfully() {

        String firstName = TestDataFactory.generateUniqueFirstName();
        String lastName = TestDataFactory.generateUniqueLastName();
        String employeeId = TestDataFactory.generateEmployeeId();

        EmployeePage employeePage = new EmployeePage(page);

        employeePage.goToPim();
        employeePage.goToAddEmployee();
        employeePage.addEmployee(firstName, lastName, employeeId);

        employeePage.goToEmployeeList();
        employeePage.searchByEmployeeIdAndSelect(employeeId);

        assertTrue(employeePage.recordsContainEmployee(employeeId),
                "Expected employee with ID " + employeeId + " to be deleted");
    }

}
