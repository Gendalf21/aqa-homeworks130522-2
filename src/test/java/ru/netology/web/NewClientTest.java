package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class NewClientTest {
    @Test
    void shouldCreateNewValidActiveClient() {
        open("http://localhost:9999");
        RegistrationInfo validClient = DataGenerator.generateValidActiveClient();
        $("[name=login]").setValue(validClient.getLogin());
        $("[name=password]").setValue(validClient.getPassword());
        $(".button__text").click();
        $(".heading").shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldCreateNewValidBlockedClient() {
        open("http://localhost:9999");
        RegistrationInfo validClient = DataGenerator.generateValidBlockedClient();
        $("[name=login]").setValue(validClient.getLogin());
        $("[name=password]").setValue(validClient.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldCreateClientWithIncorrectLogin() {
        open("http://localhost:9999");
        RegistrationInfo validClient = DataGenerator.generateClientWithIncorrectLogin();
        $("[name=login]").setValue(validClient.getLogin());
        $("[name=password]").setValue(validClient.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateClientWithIncorrectPassword() {
        open("http://localhost:9999");
        RegistrationInfo validClient = DataGenerator.generateClientWithIncorrectPassword();
        $("[name=login]").setValue(validClient.getLogin());
        $("[name=password]").setValue(validClient.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

}