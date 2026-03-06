package com.chukwuma.sdet.tests.ui.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.chukwuma.sdet.tests.ui.selenium.utils.ElementActions;
import com.chukwuma.sdet.tests.ui.selenium.utils.WaitUtils;

public class PimPage {

    private WebDriver driver;

    private By addEmployeeBtn = By.xpath("//a[normalize-space()='Add Employee']");
    private By employeeListBtn = By.xpath("//a[normalize-space()='Employee List']");

    private By firstNameLocator = By.name("firstName");
    private By lastNameLocator = By.name("lastName");
    private By employeeIdLocator = By
            .xpath("//label[text()='Employee Id']/ancestor::div[contains(@class,'oxd-input-group')]//input");
    private By saveBtn = By.cssSelector("button[type='submit']");

    private By fullNameLocator = By.className("orangehrm-edit-employee-name");

    private By loader = By.cssSelector(".oxd-form-loader");
    private By tableHeader = By.className("oxd-table-filter-header-title");
    private By tableRows = By.cssSelector(".oxd-table-body .oxd-table-row");
    private By searchBtn = By.xpath("//button[normalize-space()='Search']");

    private By deleteModalBtn = By.className("oxd-button--label-danger");
    private By successFulDeleteToaster = By.cssSelector(".oxd-toast-container .oxd-toast--success");

    public PimPage(WebDriver driver) {
        this.driver = driver;
        waitForPageReady();
    }

    public void clickAddEmployee() {
        ElementActions.click(driver, addEmployeeBtn);
    }

    public void clickEmployeeList() {
        ElementActions.click(driver, employeeListBtn);
    }

    public void addEmployee(String firstName, String lastName, String employeeId) {

        ElementActions.type(driver, firstNameLocator, firstName);
        ElementActions.type(driver, lastNameLocator, lastName);
        ElementActions.type(driver, employeeIdLocator, employeeId);

        ElementActions.click(driver, saveBtn);
        waitForPageReady();
        WaitUtils.waitForVisible(driver, fullNameLocator, 20);
    }

    public boolean isEmployeeAdded(String firstName, String lastName) {
        waitForPageReady();
        WaitUtils.waitForVisible(driver, fullNameLocator, 20);
        String employeeName = ElementActions.getText(driver, fullNameLocator);
        return employeeName.contains(firstName) && employeeName.contains(lastName);
    }

    public boolean isEmployeeDeleted(String firstName, String lastName) {
        By container = By.className("orangehrm-paper-container");
        WaitUtils.waitForVisible(driver, container, 10);
        String containerText = ElementActions.getText(driver, container);

        return !(containerText.contains(firstName) && containerText.contains(lastName));
    }

    public void searchEmployee(String employeeId) {

        WaitUtils.waitForVisible(driver, tableHeader, 10);
        ElementActions.type(driver, employeeIdLocator, employeeId);
        driver.findElement(searchBtn).click();

        waitForPageReady();
        WaitUtils.waitForVisible(driver, tableRows, 10);
    }

    public void deleteEmployee(String employeeId) {
        By deleteBtn = deleteBtnByEmployeeId(employeeId);
        WaitUtils.waitForVisible(driver, deleteBtn, 10);
        driver.findElement(deleteBtn).click();

        WaitUtils.waitForVisible(driver, deleteModalBtn, 10);
        driver.findElement(deleteModalBtn).click();

        WaitUtils.waitForVisible(driver, successFulDeleteToaster, 10);
    }

    private void waitForPageReady() {
        WaitUtils.waitForInvisible(driver, loader, 10);
    }

    private By deleteBtnByEmployeeId(String id) {
        return By.xpath("//div[contains(@class,'oxd-table-row')][.//div[text()='" + id
                + "']]//button[.//i[contains(@class,'bi-trash')]]");
    }
}
