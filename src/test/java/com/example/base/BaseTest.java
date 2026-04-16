package com.example.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://www.saucedemo.com";
        Configuration.screenshots = true;
        Configuration.holdBrowserOpen = false;

        // ОЧЕНЬ ВАЖНО: добавить listener ДО создания драйвера
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false));

        System.out.println("✅ Allure listener добавлен");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}