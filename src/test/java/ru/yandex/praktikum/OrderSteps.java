package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.models.Order;
import static io.restassured.RestAssured.given;

public class OrderSteps {
    private static final String BASE_URI = "https://qa-scooter.praktikum-services.ru";
    private static final String ORDER_PATH = "/api/v1/orders";

    @Step("Создание заказа с данными: firstName = {order.firstName}, lastName = {order.lastName}, цвет = {order.color}")
    public Response createOrder(Order order) {
        return given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(order)
                .post(ORDER_PATH);
    }
    @Step("Получение списка заказов")
    public Response getOrders() {
        return given()
                .baseUri(BASE_URI)
                .get(ORDER_PATH);
    }
}//vot
