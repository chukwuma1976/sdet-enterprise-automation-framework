package com.chukwuma.sdet.pages;

import com.microsoft.playwright.Page;

public class UserManagementPage {

    private final Page page;

    public UserManagementPage(Page page) {
        this.page = page;
    }

    public void clickAddUser() {
        page.click("text=Add User");
    }

    public void addUser(String email, String password) {
        page.fill("#email", email);
        page.fill("#password", password);
        page.click("text=Save");
    }

    public void searchUser(String email) {
        page.fill("#search", email);
    }

    public boolean isUserVisible(String email) {
        return page.locator("text=" + email).isVisible();
    }

    public void deleteUser(String email) {
        page.locator("text=" + email)
                .locator("..")
                .locator("text=Delete")
                .click();
    }
}