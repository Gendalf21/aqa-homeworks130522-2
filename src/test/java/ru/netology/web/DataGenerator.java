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

        public static void requestForm(RegistrationInfo registrationInfo) {
            given()
                    .spec(requestSpec)
                    .body(registrationInfo)
                    .when()
                    .post("/api/system/users")
                    .then()
                    .statusCode(200);
        }

        public static RegistrationInfo generateValidActiveClient() {
            Faker faker = new Faker(new Locale("en"));
            String login = faker.name().firstName();
            String password = faker.internet().password();
            String status = "active";
            RegistrationInfo newClient = new RegistrationInfo (login, password, status);
            requestForm(newClient);
            return newClient;

        }

        public static RegistrationInfo generateValidBlockedClient() {
            Faker faker = new Faker(new Locale("en"));
            String login = faker.name().firstName();
            String password = faker.internet().password();
            String status = "blocked";
            RegistrationInfo newClient = new RegistrationInfo(login, password, status);
            requestForm(newClient);
            return newClient;
        }

        public static RegistrationInfo generateClientWithIncorrectLogin() {
            Faker faker = new Faker(new Locale("en"));
            String login = "maya";
            String password = faker.internet().password();
            String status = "active";
            RegistrationInfo newClient = new RegistrationInfo(login, password, status);
            requestForm(newClient);
            return new RegistrationInfo("mayyaa", password, status);
        }

        public static RegistrationInfo generateClientWithIncorrectPassword() {
            Faker faker = new Faker(new Locale("en"));
            String login = faker.name().firstName();
            String password = "password";
            String status = "active";
            RegistrationInfo newClient = new RegistrationInfo(login, password, status);
            requestForm(newClient);
            return new RegistrationInfo(login, "incorrectpassword", status);
        }

}