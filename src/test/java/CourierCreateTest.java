import courier.Courier;
import courier.CourierCreateExample;
import courier.CourierId;
import courier.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static randomCourier.ChooseCourier.chooseRandomCourier;


public class CourierCreateTest extends Specification{
    private CourierCreateExample courierCreateExample;
    private int id;

    @Before
    public void setUp() {
        RestAssured.requestSpecification = this.requestSpec;
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Успешное создание курьера при вводе валидных данных")
    public void createCourier() {
        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        Response response = courierCreateExample.createCourier(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .body("ok", equalTo(true));

    }

    @Test
    @DisplayName("Создание курьера без логина")
    @Description("Создание курьера без ввода обязательного поля логин")

    public void createCourierWithoutLogin() {
        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        Response response = courierCreateExample.createNoLogin(courier);
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без пароля")
    @Description("Создание курьера при вводе без ввода обязательного поля пароль")
    public void createCourierWithoutPassword() {
        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        Response response = courierCreateExample.createNoPassword(courier);
        response.then().assertThat().statusCode(SC_BAD_REQUEST)
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание одинаковых курьеров")
    @Description("Создание курьера с валидными данными и повторное создание этого же курьера")
    public void createDoubleCouriers() {
        Courier courier = chooseRandomCourier();
        courierCreateExample = new CourierCreateExample();
        courierCreateExample.createCourier(courier);
        Response response = courierCreateExample.createCourier(courier);
        Response loginResponse = courierCreateExample.login(courier);
        id = loginResponse.as(CourierId.class).getId();

        response
                .then()
                .assertThat()
                .statusCode(SC_CONFLICT)
                .assertThat()
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        courierCreateExample.delete(id);
    }
}
