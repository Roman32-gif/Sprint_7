import courier.Specification;
import courier.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.util.Optional;
import order.Order;
import order.OrderCreateExample;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static java.util.Optional.empty;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.not;

public class CreateOrderNoColour extends Specification {
    private OrderCreateExample orderCreateExample;
    private static final String FIRST_NAME = "Viktor";
    private static final String LAST_NAME = "Petrovich";
    private static final String ADDRESS = "Pushkina Street";
    private static final String METRO_STATION = "Menedeleeva";
    private static final String PHONE = "+86543423366";
    private static final int RENT_TIME = 2;
    private static final String DELIVERY_DATE = "2024-11-10";
    private static final String COMMENT = "Chill and relax";
    String track;

    @Before
    public void setUp() {
        RestAssured.requestSpecification = this.requestSpec;
    }

    @Test
    @DisplayName("Создание заказа без выбора цвета")
    @Description("Для создания заказа не обязательно выбирать цвет")
    public void createOrderWithoutColour() {
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT);
        Response response = orderCreateExample.createOrder(order);
        track = response
                .then()
                .extract()
                .path("track").toString();

        response
                .then()
                .assertThat()
                .statusCode(SC_CREATED)
                .assertThat()
                .body("track", not(empty()));
    }

}
