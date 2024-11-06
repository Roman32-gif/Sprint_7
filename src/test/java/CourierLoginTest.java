import courier.Courier;
import courier.CourierCreateExample;
import courier.CourierId;
import courier.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static randomCourier.ChooseCourier.chooseRandomCourier;

public class CourierLoginTest extends Specification {
    private CourierCreateExample courierCreateExample;
    private int id;

    @Before
    public void setUp() {
        RestAssured.requestSpecification = this.requestSpec;
    }

    @Test
    @DisplayName("Логин курьера в системе")
    @Description("Успешная авторизация в аккаунт курьера, введя все обязательные поля ")
    public void loginCourier() {

        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.login(courier);
        id = response.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Логин курьера в системе без ввода логина")
    @Description("Логин не заполняется. Авторизация не проходит, так как необходимо заполнить все обязательные поля")
    public void loginCourierWithoutLogin(){

        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.loginNoLogin(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Логин курьера в системе без ввода пароля")
    @Description("Пароль не заполняется. Авторизация не проходит, так как необходимо заполнить все обязательные поля")
    public void loginCourierWithoutPassword() {

        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.loginNoPassword(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Логин курьера в системе при вводе несуществующего логина")
    @Description("Вводится несуществующий логин. Авторизация не проходит, так как курьера с таким логином нет")
    public void authorizationCourierWithNonExistentLogin() {

        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.loginWithUnknownLogin(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Логин курьера в системе при вводе несуществующего пароля")
    @Description("Вводится несуществующий пароль. Авторизация не проходит, так как такого пароля не существует")
    public void authorizationCourierWithNonExistentPassword() {

        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.loginWithUnknownPassword(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .assertThat()
                .body("message", equalTo("Учетная запись не найдена"));

    }

    @After
    public void tearDown() {
        courierCreateExample.delete(id);
    }

}
