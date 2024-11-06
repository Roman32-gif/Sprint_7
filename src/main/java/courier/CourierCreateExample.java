package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static courier.CourierFakeInfo.*;
import static courier.CourierNoLogin.*;
import static courier.CourierNoLogin.getPassword;
import static courier.CourierNoPassword.*;
import static courier.CourierNoPassword.getLogin;
import static io.restassured.RestAssured.given;

public class CourierCreateExample {
    private static final String CREATE_URL = "api/v1/courier";
    private static final String LOGIN_URL = "api/v1/courier/login";

    @Step("Создание курьера")
    public static Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(CREATE_URL);
    }

    @Step("Авторизация курьера")
    public static Response login(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(dataFromCourier(courier))
                .when()
                .post(LOGIN_URL);
    }

    @Step("Создание курьера без ввода логина")
    public static Response createNoLogin(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getPassword(courier))
                .when()
                .post(CREATE_URL);
    }

    @Step("Создание курьера без ввода пароля")
    public static Response createNoPassword(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getLogin(courier))
                .when()
                .post(CREATE_URL);
    }

    @Step("Авторизация курьера без ввода логина")
    public static Response loginNoLogin(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getPassword(courier))
                .when()
                .post(LOGIN_URL);
    }

    @Step("Авторизация курьера без ввода пароля")
    public static Response loginNoPassword(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(getLogin(courier))
                .when()
                .post(LOGIN_URL);
    }

    @Step("Авторизация курьера с несуществующим логином")
    public static Response loginWithUnknownLogin(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(fakeLoginFromCourier(courier))
                .when()
                .post(LOGIN_URL);
    }

    @Step("Авторизация курьера с несуществующим паролем")
    public static Response loginWithUnknownPassword(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(fakePasswordFromCourier(courier))
                .when()
                .post(LOGIN_URL);
    }

    @Step("Удаление курьера")
    public static Response delete(int id) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(CREATE_URL + "/" + id);
    }
}
