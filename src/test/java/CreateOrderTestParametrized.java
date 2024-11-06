import courier.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.OrderCreateExample;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTestParametrized extends Specification {
    private static final String FIRST_NAME = "Viktor";
    private static final String LAST_NAME = "Petrovich";
    private static final String ADDRESS = "Pushkina Street";
    private static final String METRO_STATION = "Menedeleeva";
    private static final String PHONE = "+86543423366";
    private static final int RENT_TIME = 2;
    private static final String DELIVERY_DATE = "2024-11-10";
    private static final String COMMENT = "Chill and relax";
    private final String[] colors;
    String track;
    private OrderCreateExample orderCreateExample;

    public CreateOrderTestParametrized(String[] colors) {
        this.colors = colors;
    }

    @Before
    public void setUp() {
        RestAssured.requestSpecification = this.requestSpec;
    }

    @Parameterized.Parameters(name = "colour = ''{0}''")
    public static Object[][] orderCreationParameters() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Создание заказа с заполнением полей валидными значениями")
    public void createOrder() {
        Order order = new Order(FIRST_NAME, LAST_NAME, ADDRESS, METRO_STATION, PHONE, RENT_TIME, DELIVERY_DATE, COMMENT, colors);
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
                .body("track", notNullValue());
    }

}
