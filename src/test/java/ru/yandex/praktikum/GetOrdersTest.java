package ru.yandex.praktikum;


import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersTest {

    private final OrderSteps steps = new OrderSteps();

    @Test
    public void getOrdersReturnsList() {

        Response response = steps.getOrders();

        response.then().statusCode(200)
                .body("orders", notNullValue());
    }

}
