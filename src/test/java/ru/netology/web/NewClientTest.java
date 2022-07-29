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
        RegistrationInfo BlockedUser = DataGenerator.getRegisteredUser("blocked");
        $("[name=login]").setValue(BlockedUser.getLogin());
        $("[name=password]").setValue(BlockedUser.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldCreateIncorrectLogin() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo IncorrectLogin = DataGenerator.getUserIncorrectLogin("active");
        $("[name=login]").setValue(IncorrectLogin.getLogin());
        $("[name=password]").setValue(IncorrectLogin.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateIncorrectPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo IncorrectPass = DataGenerator.getUserIncorrectPass("active");
        $("[name=login]").setValue(IncorrectPass.getLogin());
        $("[name=password]").setValue(IncorrectPass.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldCreateIncorrectLoginPass() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        RegistrationInfo IncorrectLoginPass = DataGenerator.getUserIncorrectLoginPass("active");
        $("[name=login]").setValue(IncorrectLoginPass.getLogin());
        $("[name=password]").setValue(IncorrectLoginPass.getPassword());
        $(".button__text").click();
        $(".notification__content").shouldBe(Condition.visible, Duration.ofMillis(5000)).
                shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"));
    }

}