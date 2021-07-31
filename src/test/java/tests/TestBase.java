package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import extensions.AllureScreenshot;
import helpers.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({AllureJunit5.class, AllureScreenshot.class})
public class TestBase {
    @BeforeAll
    static void setUp() {
        Configuration.browser = System.getProperty("web.browser", "chrome");
    }

    @BeforeEach
    void addListeners() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(false).savePageSource(false));
    }

    @AfterEach
    void addAttachments() {
        Allure.attachPageSource();
    }
}
