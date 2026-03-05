package com.chukwuma.sdet.tests.ui.playwright;

import com.chukwuma.sdet.base.BaseTest;
import com.chukwuma.sdet.components.LeftNavigationBarComponent;
import com.chukwuma.sdet.core.auth.AuthHelper;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("regression")
@Tag("ui")
@Epic("Navigation UI Tests")
@Feature("Left Navigation Bar")
class LeftNavigationBarTest extends BaseTest {

    private LeftNavigationBarComponent nav;

    @BeforeEach
    void setup() {
        new AuthHelper(page).loginAsDefaultUser();
        nav = new LeftNavigationBarComponent(page);
    }

    static Stream<NavData> navigationData() {
        return Stream.of(
                new NavData("Admin", "Admin"),
                new NavData("PIM", "PIM"),
                new NavData("Leave", "Leave"),
                new NavData("Time", "Time"),
                new NavData("Recruitment", "Recruitment"),
                new NavData("My Info", "PIM"), // Exception
                new NavData("Performance", "Performance"),
                new NavData("Dashboard", "Dashboard"),
                new NavData("Directory", "Directory"), // h6 exception
                new NavData("Maintenance", "Maintenance"));
    }

    @ParameterizedTest
    @MethodSource("navigationData")
    @DisplayName("Should navigate to correct header when clicking on left navigation tabs")
    @Description("Verify that clicking on each left navigation tab leads to the correct page header being displayed")
    void shouldNavigateToCorrectHeader(NavData data) {

        nav.clickTab(data.tabName());

        if (data.tabName().equals("Maintenance")) {
            nav.closeMaintenanceModalIfPresent();
            return;
        }

        assertTrue(nav.isHeaderVisible(data.expectedHeader()),
                "Expected header: " + data.expectedHeader());
    }

    record NavData(String tabName, String expectedHeader) {
    }
}