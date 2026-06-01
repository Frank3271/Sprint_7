package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoginCourierTest extends  BaseTest {

    private Courier courier;

    @Before
    public void setUp() {
               String login = "log_" + System.currentTimeMillis();
        String password = "pass123";
        courier = new Courier(login, password, "Имя");
        courierSteps.createCourier(courier);
        Response loginResp = courierSteps.loginCourier(courier);
        courierId = courierSteps.getCourierId(loginResp);
    }

    @Test
    public void loginSuccess() {
        Response response = courierSteps.loginCourier(courier);
        response.then().statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    public void loginWrongPassword() {

        courier.setPassword("wrong");
        Response response = courierSteps.loginCourier(courier);
        response.then().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void loginNonExistentUser() {
        Courier fake = new Courier("fake", "123", null);
        Response response = courierSteps.loginCourier(fake);
        response.then().statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void loginWithoutLogin() {
        courier.setLogin(null);
        Response response = courierSteps.loginCourier(courier);
        response.then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
//vot