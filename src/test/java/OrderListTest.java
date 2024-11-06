import courier.Specification;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import java.util.Optional;
import order.OrderCreateExample;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;


import static java.util.Optional.empty;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.not;


public class OrderListTest extends Specification {
    private OrderCreateExample orderCreateExample;

    @Before
    public void setUp() {
        RestAssured.requestSpecification = this.requestSpec;
        orderCreateExample = new OrderCreateExample();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Получение всех заказов")
    public void getOrderList() {
        Response response = OrderCreateExample.getOrderList();
        response
                .then()
                .assertThat()
                .statusCode(SC_OK)
                .assertThat()
                .body("orders", not(empty()));

    }
}