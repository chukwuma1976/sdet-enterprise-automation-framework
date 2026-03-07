package com.chukwuma.sdet.tests.ui.selenium.base;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.chukwuma.sdet.extensions.ScreenshotOnFailureExtension;

@ExtendWith(ScreenshotOnFailureExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        boolean isCI = System.getenv("CI") != null;
        ChromeOptions options = new ChromeOptions();
        if (isCI) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}