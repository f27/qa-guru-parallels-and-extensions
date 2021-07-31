package extensions;

import com.codeborne.selenide.Selenide;
import helpers.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AllureScreenshot implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (context.getExecutionException().isPresent()) {
            Allure.attachScreenshot("Screenshot on fail");
        }
        Selenide.closeWebDriver();
    }
}
