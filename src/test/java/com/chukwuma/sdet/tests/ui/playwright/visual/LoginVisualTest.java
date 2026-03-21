package com.chukwuma.sdet.tests.ui.playwright.visual;

import com.chukwuma.sdet.config.ConfigReader;
import com.chukwuma.sdet.utils.VisualComparisonUtils;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("visual")
@Epic("Visual Tests")
public class LoginVisualTest extends PlaywrightVisualBaseTest {

    @Test
    void loginPageVisualTest() throws Exception {

        File baselineFile = new File("src/test/resources/visual-baselines/login-page.png");

        if (!baselineFile.exists()) {
            throw new RuntimeException("Baseline image is missing!");
        }

        page.navigate(ConfigReader.get("BASE_URL"));

        page.waitForLoadState();
        page.waitForTimeout(1000);

        // Disable animations
        page.addStyleTag(new com.microsoft.playwright.Page.AddStyleTagOptions()
                .setContent("* { transition: none !important; animation: none !important; }"));

        // Take screenshot
        byte[] screenshotBytes = page.screenshot(new com.microsoft.playwright.Page.ScreenshotOptions()
                .setFullPage(true));

        BufferedImage actualImage = ImageIO.read(new ByteArrayInputStream(screenshotBytes));

        BufferedImage expectedImage = VisualComparisonUtils.loadImage(
                "src/test/resources/visual-baselines/login-page.png");

        ImageComparisonResult result = VisualComparisonUtils.compareImages(expectedImage, actualImage);

        // Attach actual screenshot
        Allure.addAttachment("Actual Screenshot", "image/png",
                new ByteArrayInputStream(screenshotBytes), ".png");

        double diffPercentage = result.getDifferencePercent();
        double threshold = 0.5;

        System.out.println("Login Page Diff %: " + diffPercentage);

        // Attach diff image if exists
        if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
            ByteArrayOutputStream diffStream = new ByteArrayOutputStream();
            ImageIO.write(result.getResult(), "png", diffStream);

            Allure.addAttachment("Diff Image", "image/png",
                    new ByteArrayInputStream(diffStream.toByteArray()), ".png");
        }

        assertTrue(diffPercentage < threshold,
                "Visual regression detected! Diff: " + diffPercentage + "%");
    }
}
