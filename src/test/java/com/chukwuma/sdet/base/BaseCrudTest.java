package com.chukwuma.sdet.base;

import org.junit.jupiter.api.AfterEach;

import com.chukwuma.sdet.pages.EmployeePage;

public abstract class BaseCrudTest extends BaseTest {

    protected String employeeId;

    @AfterEach
    void cleanupEmployee() {
        if (employeeId != null) {
            try {
                EmployeePage employeePage = new EmployeePage(page);
                employeePage.goToEmployeeList();
                employeePage.searchByEmployeeIdAndSelect(employeeId);
                employeePage.deleteEmployee(employeeId);
            } catch (Exception e) {
                System.out.println("Cleanup skipped for ID: " + employeeId);
            }
        }
    }
}