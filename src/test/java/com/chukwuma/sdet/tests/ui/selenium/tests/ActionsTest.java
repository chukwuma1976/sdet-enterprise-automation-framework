package com.chukwuma.sdet.tests.ui.selenium.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;
import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.tests.ui.selenium.base.BaseTest;
import com.chukwuma.sdet.tests.ui.selenium.pages.DragAndDropCirclesPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.HoverPage;
import com.chukwuma.sdet.tests.ui.selenium.pages.SliderPage;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Tag("selenium")
@Tag("ui")
@Epic("Selenium Actions")
@Feature("Demonstrate Selenium Actions")
public class ActionsTest extends BaseTest {

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Demonstrate drag and drop")
    void demonstrateDragAndDrop() {
        driver.get(ConfigReader.get("PRACTICE_TESTING_URL") +
                "drag-and-drop-circles");
        DragAndDropCirclesPage testingPage = new DragAndDropCirclesPage(driver);
        testingPage.dragAndDropCircles();
        testingPage.confirmCirclesAreInTarget();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Demonstrate drag and drop")
    void demonstrateSlider() {
        driver.get(ConfigReader.get("PRACTICE_TESTING_URL") + "horizontal-slider");
        SliderPage testingPage = new SliderPage(driver);
        testingPage.slideElement();
    }

    @RetryingTest(maxAttempts = 3)
    @DisplayName("Demonstrate hover")
    void demonstrateHover() {
        driver.get(ConfigReader.get("PRACTICE_TESTING_URL") + "hovers");
        new HoverPage(driver).hoverOverElements();
    }

}
