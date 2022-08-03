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
        $(".heading").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Личный кабинет"));
    }

    @Test
    void shouldCreateBlockedClient() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo blockedUser = DataGenerator.getRegisteredUser("blocked");
        $("[name=login]").setValue(blockedUser.getLogin());
        $("[name=password]").setValue(blockedUser.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldCreateIncorrectLoginCorrectPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo correctPass = DataGenerator.getRegisteredUser("active");
        $("[name=login]").setValue(DataGenerator.getLogin());
        $("[name=password]").setValue(correctPass.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateCorrectLoginIncorrectPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo correctLogin = DataGenerator.getRegisteredUser("active");
        $("[name=login]").setValue(correctLogin.getLogin());
        $("[name=password]").setValue(DataGenerator.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateIncorrectLoginIncorrectPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[name=login]").setValue(DataGenerator.getLogin());
        $("[name=password]").setValue(DataGenerator.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

}