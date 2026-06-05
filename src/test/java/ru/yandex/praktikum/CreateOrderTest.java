package ru.yandex.praktikum;


import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.models.Order;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;



@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> colors;


    public CreateOrderTest(List<String> colors) {
        this.colors = colors;
    }


    @Parameterized.Parameters(name = "Цвета заказа: {0}")
    public static Object[][] data() {
        return new Object[][]{
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLACK", "GREY")},
                {Collections.emptyList()}
        };
    }

    @Test
    public void createOrderWithColors() {

        Order order = new Order(
                "Иван",
                "Иванов",
                "Москва, ул. Пушкина, д. 1",
                "Лубянка",
                "+79998887766",
                5,
                "2025-06-15",
                "Позвонить за час",
                colors
        );//vot

        OrderSteps steps = new OrderSteps();
        Response response = steps.createOrder(order);

        response.then().statusCode(201).body("track", notNullValue());
    }

}
