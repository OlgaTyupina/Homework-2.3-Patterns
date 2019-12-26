import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestCardDelivery {
    @Test
    void shouldFillInTheForm() {
        open("http://localhost:9999");
        SelenideElement form = $ ("form.form");
        form.$("[data-test-id=city] input").setValue(DataGeneration.getCity());
        form.$("[data-test-id=date] input").setValue(DataGeneration.getNewDate());
        form.$("[data-test-id=name] input").setValue(DataGeneration.getName());
        form.$("[data-test-id=phone] input").setValue(DataGeneration.getPhone());
        form.$("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000);
        DataGeneration.cleanUp();
        form.$("[data-test-id=date] input").setValue(DataGeneration.getFutureDate());
        $(".button__text").click();
        $("[data-test-id='replan-notification']").waitUntil(Condition.visible,
                5000);
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(Condition.visible, 15000);
    }
}
