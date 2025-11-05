package ru.yandex.practicum.pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;
    private final By accordionPanel = By.cssSelector(".accordion__panel");
    private final  By errorTitle = By.cssSelector(".accordion__button");
    private final By accordion = By.cssSelector(".accordion");
    private final By accordionItem = By.cssSelector(".accordion__item");


    public MainPage(WebDriver driver){
        this.driver = driver;


    }

    public void closeAccordeonBody(WebDriver driver, WebElement e, int j){
        WebElement divElementHeader = e.findElement(By.id("accordion__heading-"+j));
        WebElement divElementPanel = driver.findElement(By.id("accordion__panel-"+j));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('aria-disabled', 'false');", divElementHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('aria-expanded', 'false');", divElementHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('hidden', 'true');", divElementPanel);

    }


    public String getBodyText(WebElement e){
       return e.findElement(accordionPanel).getText();
    }


    public String getTitleText(WebElement e, WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorTitle));
        Assert.assertTrue(driver.findElement(errorTitle).isDisplayed());

        return e.findElement(errorTitle).getText();
    }


    public void openAccordeonBody(WebDriver driver, WebElement e, int j){
        WebElement divElementHeader = e.findElement(By.id("accordion__heading-"+j));
        WebElement divElementPanel = driver.findElement(By.id("accordion__panel-"+j));
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('aria-disabled', 'true');", divElementHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('aria-expanded', 'true');", divElementHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('hidden');", divElementPanel);

    }


    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);

    }


    public void scrollToAccordion() {
        WebElement element = driver.findElement(accordion);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }


    public void findAndCompareAccordeonElements() {

        List<WebElement> elements = driver.findElements(accordionItem);
        int j=0;
        for (WebElement e : elements) {

            openAccordeonBody(driver, e, j);
            String titleText = getTitleText(e, driver);
            String bodyText = getBodyText(e);

            System.out.println("-------------------------------");
            System.out.println("Проверка FAQ #"+(j+1));

            if(compareTitleElements(titleText, j)) {

                if(compareBodyElements(bodyText, j)){
                    System.out.println("Тест FAQ #"+(j+1)+" пройден.");
                }else{
                    System.out.println("Тест FAQ #"+(j+1)+" не пройден.");
                }
            }else{
                System.out.println("Проверка FAQ #"+(j+1)+" не пройден.");
            }
            closeAccordeonBody(driver, e, j);

            j++;

        }
    }


    public boolean compareTitleElements(String elem, int i){
        return  referenceTitleText(i).equals(elem);
    }


    public boolean compareBodyElements(String elem, int i){
        return  referenceBodyText(i).equals(elem);
    }


    public String referenceTitleText(int numberFAQ) {
        switch (numberFAQ) {
            case 0:
                return "Сколько это стоит? И как оплатить?";
            case 1:
                return "Хочу сразу несколько самокатов! Так можно?";

            case 2:
                return "Как рассчитывается время аренды?";
            case 3:
                return "Можно ли заказать самокат прямо на сегодня?";

            case 4:
                return "Можно ли продлить заказ или вернуть самокат раньше?";

            case 5:
                return "Вы привозите зарядку вместе с самокатом?";

            case 6:
                return "Можно ли отменить заказ?";

            case 7:
                return "Я жизу за МКАДом, привезёте?";

            default:
                return "Что-то пошло не так";

        }
    }

    public String referenceBodyText(int numberFAQ) {
        switch (numberFAQ) {
            case 0:
                return "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
            case 1:
                return "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
            case 2:
                return "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
            case 3:
                return "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
            case 4:
                return "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
            case 5:
                return "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";

            case 6:
                return "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";

            case 7:
                return "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

            default:
                return "Что-то пошло не так";

        }
    }


}
