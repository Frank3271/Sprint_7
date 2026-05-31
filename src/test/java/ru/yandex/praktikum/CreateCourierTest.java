package ru.yandex.praktikum;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.models.Courier;
import static org.hamcrest.Matchers.equalTo;


public class CreateCourierTest extends BaseTest {
    private Courier courier;
    private String uniqueLogin;

    @Before
    public void setUp() {
        uniqueLogin = "courier_" + System.currentTimeMillis();
        courier = new Courier(uniqueLogin, "1234", "Миша");

    }
@Test
    public void createCourierSuccess() {
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(201).body("ok", equalTo(true));

        Response loginResp = courierSteps.loginCourier(courier);
        courierId = courierSteps.getCourierId(loginResp);

}

@Test
public void createSameCourierConflict() {
    courierSteps.createCourier(courier).then().statusCode(201);
    Response loginResp = courierSteps.loginCourier(courier);
    courierId = courierSteps.getCourierId(loginResp);

    Response second = courierSteps.createCourier(courier);
        second.then().statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
}


    @Test
    public void createCourierWithoutPassword() {
        courier.setPassword(null);
        Response response = courierSteps.createCourier(courier);
        response.then().statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}
