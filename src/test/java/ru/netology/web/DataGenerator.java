package ru.netology.web;

import static io.restassured.RestAssured.given;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;


public class DataGenerator {

    private static Faker faker = new Faker(new Locale("en"));

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void requestForm(RegistrationInfo info) {

        given()
                .spec(requestSpec)
                .body(new RegistrationInfo(info.getLogin(), info.getPassword(), info.getStatus()))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getLogin() {
        return faker.name().username();
    }

    public static String getPassword() {
        return faker.internet().password();
    }

    public static RegistrationInfo getUser(String status) {
        return new RegistrationInfo(getLogin(), getPassword(), status);
    }

    public static RegistrationInfo getRegisteredUser(String status) {
        RegistrationInfo registeredUser = getUser(status);
        requestForm(registeredUser);
        return registeredUser;
    }
}