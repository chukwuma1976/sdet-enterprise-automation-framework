package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

public class EmployeePage {

    private final Page page;

    public EmployeePage(Page page) {
        this.page = page;
    }

    public void goToPim() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("PIM")).click();
    }

    public void goToAddEmployee() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Add Employee")).click();
    }

    public void goToEmployeeList() {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("Employee List")).click();
    }

    public void addEmployee(String firstName, String lastName) {

        page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("First Name"))
                .fill(firstName);

        page.getByRole(AriaRole.TEXTBOX,
                new Page.GetByRoleOptions().setName("Last Name"))
                .fill(lastName);

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Save"))
                .click();

        page.waitForURL("**/viewPersonalDetails/**");
    }

    public void searchAndSelectEmployee(String fullName) {

        Locator searchBox = page
                .getByRole(AriaRole.TEXTBOX,
                        new Page.GetByRoleOptions().setName("Type for hints..."))
                .first();

        searchBox.fill(fullName);

        Locator listbox = page.getByRole(AriaRole.LISTBOX);
        listbox.waitFor();

        Locator option = listbox
                .getByRole(AriaRole.OPTION)
                .filter(new Locator.FilterOptions().setHasText(fullName));

        option.click();

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Search"))
                .click();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void searchByNameWithoutSelecting(String fullName) {

        Locator searchBox = page
                .getByRole(AriaRole.TEXTBOX,
                        new Page.GetByRoleOptions().setName("Type for hints..."))
                .first();

        searchBox.fill(fullName);

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Search"))
                .click();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public void deleteEmployee(String fullName) {

        Locator row = page.getByRole(AriaRole.ROW)
                .filter(new Locator.FilterOptions().setHasText(fullName));

        row.locator("button:has(.bi-trash)").click();

        page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Yes, Delete"))
                .click();

        // 🔴 IMPORTANT — wait for success toast
        page.getByText("Successfully Deleted").waitFor();

        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

    public boolean isEmployeePresent(String fullName) {

        Locator row = page.getByRole(AriaRole.ROW)
                .filter(new Locator.FilterOptions().setHasText(fullName));

        row.first().waitFor();

        return row.count() > 0;
    }

    public boolean isNoRecordsFoundVisible() {
        Locator recordsNotFound = page.locator(".orangehrm-paper-container").getByText("No Records Found");
        recordsNotFound.waitFor();
        return recordsNotFound.isVisible();
    }
}