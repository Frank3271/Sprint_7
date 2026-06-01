package ru.yandex.praktikum;

import org.junit.After;


public class BaseTest {
    protected CourierSteps courierSteps = new CourierSteps();
    protected OrderSteps orderSteps = new OrderSteps();

    protected int courierId;
    protected String courierLogin;
    protected String courierPassword;

    @After
    public void cleanUp() {
        if (courierId != 0) {
            courierSteps.deleteCourier(courierId);
        }
    }//vot


}
