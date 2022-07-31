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
    void shouldCreateIncorrectLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo incorrectLogin = DataGenerator.getRegisteredUser("active");
        //$("[name=login]").setValue(incorrectLogin.getLogin());
        $("[name=login]").setValue("alex");
        $("[name=password]").setValue(incorrectLogin.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateIncorrectPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo incorrectPass = DataGenerator.getRegisteredUser("active");
        $("[name=login]").setValue(incorrectPass.getLogin());
        $("[name=password]").setValue("pass");
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateIncorrectLoginPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo incorrectLoginPass = DataGenerator.getRegisteredUser("active");
        $("[name=login]").setValue("alex");
        $("[name=password]").setValue("pass");
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

}