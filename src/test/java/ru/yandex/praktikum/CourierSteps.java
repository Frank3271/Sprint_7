package ru.yandex.praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.models.Courier;
import static io.restassured.RestAssured.given;


public class CourierSteps {

    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";


    @Step("Создание курьера: логин = {courier.login}, пароль = {courier.password}, имя = {courier.firstName}")


    public Response createCourier(Courier courier) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(courier)
                .post(COURIER_PATH);
    }
    @Step("Логин курьера: логин = {courier.login}, пароль = {courier.password")
    public Response loginCourier(Courier courier) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(courier)
                .post(LOGIN_PATH);
    }
    @Step("Удаление курьера по id = {id}")
    public Response deleteCourier(int id) {
        return given()
                .baseUri(BASE_URL)
                .delete(COURIER_PATH + "/" + id);
    }
    @Step("Получение id курьера из ответа логина")
    public int getCourierId(Response loginResponse) {
        return loginResponse.jsonPath().getInt("id");
    }

}
//vot