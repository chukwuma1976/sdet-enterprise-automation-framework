package com.chukwuma.sdet.tests.ui.playwright.visual;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ColorScheme;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PlaywrightVisualBaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    void setup() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true));

        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720)
                .setDeviceScaleFactor(1)
                .setColorScheme(ColorScheme.LIGHT)
                .setLocale("en-US"));

        page = context.newPage();
        page.setDefaultTimeout(60000);
        context.setDefaultTimeout(60000);

        // Disable animations globally
        page.addInitScript(
                "(() => {" +
                        "const style = document.createElement('style');" +
                        "style.innerHTML = '* { transition: none !important; animation: none !important; }';" +
                        "document.head.appendChild(style);" +
                        "})()");
    }

    @AfterEach
    void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }
}