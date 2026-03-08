package com.chukwuma.sdet.pages.user;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.core.auth.AuthHelper;
import com.chukwuma.sdet.pages.EmployeePage;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;
import com.microsoft.playwright.Locator;
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

                new DashboardPage(page).logout();
        }

        private void createEssUser() {

                employeePage.goToAddEmployee();

                employeePage.addEmployeeWithOutSave(
                                ConfigReader.get("ESS_FIRST_NAME"),
                                ConfigReader.get("ESS_LAST_NAME"),
                                ConfigReader.get("ESS_USER_ID"));

                // Enable login details
                Locator loginToggle = page.locator(".oxd-switch-input");
                loginToggle.waitFor();
                loginToggle.click();

                // Wait until Username field appears
                Locator usernameField = page.getByRole(
                                AriaRole.TEXTBOX,
                                new Page.GetByRoleOptions().setName("Username"));

                usernameField.waitFor();
                usernameField.fill(ConfigReader.get("ESS_USERNAME"));

                page.locator("input[type='password']")
                                .first()
                                .fill(ConfigReader.get("ESS_PASSWORD"));

                page.locator("input[type='password']")
                                .nth(1)
                                .fill(ConfigReader.get("ESS_PASSWORD"));

                page.getByRole(
                                AriaRole.BUTTON,
                                new Page.GetByRoleOptions().setName("Save"))
                                .click();

                // Confirm creation
                page.getByText("Successfully Saved").waitFor();

                // Wait for redirect
                page.waitForURL(ConfigReader.get("view_personal_details.path"));
        }
}