package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;

import org.openqa.selenium.WebDriver;

import ru.yandex.practicum.pages.MainPage;


public class FaqTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testOpenAndCloseAccordeon() throws InterruptedException {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);

        mainPage.openMainPage();
        mainPage.scrollToAccordion();
        mainPage.findAndCompareAccordeonElements();

    }



}
