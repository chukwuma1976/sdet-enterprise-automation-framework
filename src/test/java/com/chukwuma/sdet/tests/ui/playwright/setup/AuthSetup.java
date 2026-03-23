package com.chukwuma.sdet.tests.ui.playwright.setup;

import com.chukwuma.sdet.core.auth.AuthHelper;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

public class AuthSetup {

    @Test
    void saveLoginState() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            new AuthHelper(page).loginAsDefaultUser();

            page.waitForURL("**/dashboard/index");

            // Save session state
            context.storageState(new BrowserContext.StorageStateOptions()
                    .setPath(Paths.get("src/test/resources/auth-state.json")));

            browser.close();
        }
    }
}
