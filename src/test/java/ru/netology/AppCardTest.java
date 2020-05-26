package ru.netology;

import static com.codeborne.selenide.Condition.visible;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.DataGenerator.*;

public class AppCardTest {

    @BeforeEach
    void openURL() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequest() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно")).shouldBe(visible);

        $("[data-test-id=date] input").doubleClick().sendKeys(forwardDate(4));
        $(".button__text").click();
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible);
        $("[data-test-id=replan-notification] button.button").click();
        $(withText("Успешно")).shouldBe(visible);

    }

    @Test
    void shouldSendFormWithValidData() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldGetErrorMessageIfYouSendIncorrectName() {

        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(NotCorrect().getNotCorrectName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $(".checkbox__box").click();
        $(".button__text").click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetErrorMessageIfYouSendIncorrectPhoneNumber() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(NotCorrect().getNotCorrectPhone());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldGetErrorMessageIfYouSendIncorrectCity() {
        $("[data-test-id=city] input").setValue(NotCorrect().getNotCorrectCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=\"city\"] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldGetErrorMessageIfYouSendIncorrectData() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").doubleClick().sendKeys(NotCorrect().getNotCorrectData());
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=\"date\"] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldSendFormWithoutName() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=\"name\"] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendFormWithoutNumber() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=\"phone\"] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSendFormWithoutCheckbox() {
        $("[data-test-id=city] input").setValue(makeCity());
        $("[data-test-id=date] input").sendKeys(forwardDate(3));
        $("[data-test-id=name] input").setValue(Correct().getFirstName() + " " + Correct().getLastName());
        $("[data-test-id=phone] input").setValue(Correct().getPhoneNumber());
        $(".button").click();
        $(".input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}
