package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.READ_WRITE;

public class SimpleTests extends TestBase {
    @Test
    @ResourceLock(value = "Bash.im", mode = READ)
    @DisplayName("Открыть башорг")
    void openBashTest() {
        open("https://bash.im");
        $(".header__title_name").shouldHave(text("Bash.im — Цитатник Рунета"));
    }

    @Test
    @DisplayName("Открыть гугл")
    void openGoogleTest() {
        open("https://google.ru");
        $(byName("q")).shouldBe(visible);
    }

    @Test
    @ResourceLock(value = "Bash.im", mode = READ)
    @DisplayName("Открыть башорг (должен упасть)")
    void negativeOpenBashTest() {
        open("https://bash.im");
        $(".header__title_name").shouldHave(text("Bash.im — Цытатник Рунета"));
    }

    @Test
    @ResourceLock(value = "Bash.im", mode = READ_WRITE)
    @DisplayName("Открыть башорг и что-то сделать")
    void openBashWithActionsTest() {
        open("https://bash.im");
        $(".header__title_name").shouldHave(text("Bash.im — Цитатник Рунета"));
        $(".nav__item_icon").click();
    }
}
