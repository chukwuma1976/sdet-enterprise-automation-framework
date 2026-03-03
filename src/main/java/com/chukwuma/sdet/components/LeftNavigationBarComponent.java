package com.chukwuma.sdet.components;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LeftNavigationBarComponent {

    private final Page page;

    public LeftNavigationBarComponent(Page page) {
        this.page = page;
    }

    public void clickTab(String tabName) {
        page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName(tabName))
                .click();
    }

    public boolean isHeaderVisible(String expectedHeader) {

        // Special case: Directory uses <h6>
        if (expectedHeader.equals("Directory")) {
            page.locator("h6").waitFor();
            return page.locator("h6").isVisible();
        }

        Locator header = page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions()
                        .setName(expectedHeader)
                        .setExact(true));
        header.waitFor();

        return header.isVisible();
    }

    public void closeMaintenanceModalIfPresent() {
        if (page.getByRole(AriaRole.BUTTON,
                new Page.GetByRoleOptions().setName("Cancel")).isVisible()) {
            page.getByRole(AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Cancel"))
                    .click();
        }
    }
}
