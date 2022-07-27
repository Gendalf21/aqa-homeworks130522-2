package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class NewClientTest {
    @Test
    void shouldCreateActiveClient() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo registeredUser = DataGenerator.getRegisteredUser("active");
        $("[name=login]").setValue(registeredUser.getLogin());
        $("[name=password]").setValue(registeredUser.getPassword());
        $(".button__text").click();
        $(".heading").shouldHave(Condition.exactText("Личный кабинет"));

    }

    @Test
    void shouldCreateBlockedClient() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo BlockedUser = DataGenerator.getRegisteredUser("blocked");
        $("[name=login]").setValue(BlockedUser.getLogin());
        $("[name=password]").setValue(BlockedUser.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldCreateIncorrectData() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo IncorrectData = DataGenerator.getUser("active");
        $("[name=login]").setValue(IncorrectData.getLogin());
        $("[name=password]").setValue(IncorrectData.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

}