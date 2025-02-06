package ru.yandex.praktikum.client;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import ru.yandex.praktikum.models.User;

import static io.restassured.RestAssured.given;

public class UserClient {

  @Step("Создание пользователя")
  public void requestCreateUser(User user) {
    RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    given()
            .header("Content-type", "application/json")
            .and()
            .body(user)
            .when()
            .post("/api/auth/register");
  }

  @Step("Удаление пользователя")
  public void  requestDeleteUser(User user) {
    RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    String accessToken = given()
            .header("Content-type", "application/json")
            .body(user)
            .post("/api/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .path("accessToken");
            given().header("Authorization", "Bearer" + accessToken)
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
  }

}
