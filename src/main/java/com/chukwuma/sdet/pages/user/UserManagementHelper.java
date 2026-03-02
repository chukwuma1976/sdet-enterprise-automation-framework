package com.chukwuma.sdet.pages.user;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.EmployeePage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class UserManagementHelper {

    private final Page page;
    private final EmployeePage employeePage;

    public UserManagementHelper(Page page) {
        this.page = page;
        this.employeePage = new EmployeePage(page);
    }

    public void ensureEssUserExists() {

        new AuthHelper(page).login(
                ConfigReader.get("ADMIN_USERNAME"),
                ConfigReader.get("ADMIN_PASSWORD"));

        employeePage.goToPim();
        employeePage.goToEmployeeList();

        String employeeId = ConfigReader.get("ESS_USER_ID");

        employeePage.searchByEmployeeIdAndSelect(employeeId);

        if (employeePage.isNoRecordsFoundVisible()) {
            createEssUser();
        }
    }

    private void createEssUser() {

        employeePage.goToAddEmployee();

        employeePage.addEmployeeWithOutSave(
                ConfigReader.get("ESS_FIRST_NAME"),
                ConfigReader.get("ESS_LAST_NAME"),
                ConfigReader.get("ESS_USER_ID"));

        // Enable login credentials
        page.locator(".oxd-switch-input").click();

        page.locator(".oxd-input-group:has(label:has-text('Username'))")
                .locator("input")
                .fill(ConfigReader.get("ESS_USERNAME"));

        page.locator("input[type='password']")
                .first()
                .fill(ConfigReader.get("ESS_PASSWORD"));

        page.locator("input[type='password']")
                .nth(1)
                .fill(ConfigReader.get("ESS_PASSWORD"));

        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save"))
                .click();

        page.waitForURL("**/viewPersonalDetails/**");

    }
}