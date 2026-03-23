package com.chukwuma.sdet.tests.ui.playwright.visual;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.pages.dashboard.DashboardPage;
import com.chukwuma.sdet.utils.VisualComparisonUtils;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("visual")
@Epic("Visual Tests")
public class DashboardVisualTest extends PlaywrightVisualBaseTest {

        @BeforeEach
        @Override
        protected void setup() {
                playwright = com.microsoft.playwright.Playwright.create();

                browser = playwright.chromium().launch(
                                new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(true));

                context = browser.newContext(
                                new com.microsoft.playwright.Browser.NewContextOptions()
                                                .setStorageStatePath(java.nio.file.Paths
                                                                .get("src/test/resources/auth-state.json"))
                                                .setViewportSize(1280, 720));

                page = context.newPage();
                page.setDefaultTimeout(60000);
                context.setDefaultTimeout(60000);
        }

        @Test
        void dashboardVisualTest() throws Exception {
                File baselineFile = new File("src/test/resources/visual-baselines/dashboard.png");

                if (!baselineFile.exists()) {
                        throw new RuntimeException("Baseline image is missing!");
                }

                page.navigate(ConfigReader.get("DASHBOARD_URL"));
                DashboardPage dashboard = new DashboardPage(page);

                // Wait for page shell
                page.waitForLoadState();

                // Wait for all widgets to load and all spinners to disappear
                dashboard.generateWidgetList().forEach(widget -> {
                        dashboard.isWidgetVisible(widget);
                        dashboard.waitForSpinner();
                });

                // Disable animations
                page.addStyleTag(new com.microsoft.playwright.Page.AddStyleTagOptions()
                                .setContent("* { transition: none !important; animation: none !important; }"));

                byte[] screenshotBytes = page.screenshot(
                                new com.microsoft.playwright.Page.ScreenshotOptions().setFullPage(true));

                // Add this line if new baseline dashboard image needed:
                // Files.write(Paths.get("baseline.png"), screenshotBytes);

                BufferedImage actualImage = ImageIO.read(new ByteArrayInputStream(screenshotBytes));

                BufferedImage expectedImage = VisualComparisonUtils.loadImage(
                                "src/test/resources/visual-baselines/dashboard.png");

                ImageComparisonResult result = VisualComparisonUtils.compareImages(expectedImage, actualImage);

                Allure.addAttachment("Actual Dashboard", "image/png",
                                new ByteArrayInputStream(screenshotBytes), ".png");

                double diffPercentage = result.getDifferencePercent();
                double threshold = 1.5;

                System.out.println("Dashboard Diff %: " + diffPercentage);

                if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
                        ByteArrayOutputStream diffStream = new ByteArrayOutputStream();
                        ImageIO.write(result.getResult(), "png", diffStream);

                        Allure.addAttachment("Diff Dashboard", "image/png",
                                        new ByteArrayInputStream(diffStream.toByteArray()), ".png");
                }

                assertTrue(diffPercentage < threshold,
                                "Visual regression detected! Diff: " + diffPercentage + "%");
        }
}