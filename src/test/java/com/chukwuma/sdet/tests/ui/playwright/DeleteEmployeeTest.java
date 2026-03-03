package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.utils.TestDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("ui")
@Epic("Employee UI Tests")
@Feature("CRUD operations on Employees")
class DeleteEmployeeTest extends BaseTest {

    @BeforeEach
    void login() {
        new AuthHelper(page).loginAsDefaultUser();
    }

    @Test
    @DisplayName("User can delete an existing employee")
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

        employeePage.goToEmployeeList();
        employeePage.searchByEmployeeIdAndSelect(employeeId);

        assertFalse(employeePage.recordsContainEmployee(employeeId),
                "Expected employee with ID " + employeeId + " to be deleted");
    }
}
