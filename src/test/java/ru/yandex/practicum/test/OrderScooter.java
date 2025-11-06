package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import ru.yandex.practicum.pages.OrderPage;


import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;


public class OrderScooter{

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    //Проверка возможности заказа по верхгнй кнопке
    public void testOrderScooterTopButton() throws InterruptedException {

        WebDriver driver = factory.getDriver();
        var orderPage = new OrderPage(driver);
        orderPage.startTypeOfTest(0);
        orderPage.openMainPage();
        orderPage.clickTopButtonOrder();
        orderPage.openPageOrder();
        orderPage.inputUserDataInFields();
        orderPage.clickButtonFurther();
        orderPage.inputRentDataInFields();
        orderPage.clickButtonOrder();
        orderPage.confirmClickButtonOrder();
        orderPage.confirmOrder();

    }

    @Test
    //Проверка возможности заказа по нижней кнопке
    public void testOrderScooterBottomButton() throws InterruptedException {

        WebDriver driver = factory.getDriver();
        var orderPage = new OrderPage(driver);
        orderPage.startTypeOfTest(1);
        orderPage.openMainPage();
        orderPage.scrollToButton();
        orderPage.clickBottomButtonOrder();
        orderPage.openPageOrder();
        orderPage.inputUserDataInFields();
        orderPage.clickButtonFurther();
        orderPage.inputRentDataInFields();
        orderPage.clickButtonOrder();
        orderPage.confirmClickButtonOrder();
        orderPage.confirmOrder();

    }

}
