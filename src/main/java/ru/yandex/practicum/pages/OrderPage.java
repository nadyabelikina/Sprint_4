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
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;
    private final By buttonOrderTop = By.cssSelector(".Button_Button__ra12g");
    private final By buttonOrderBottom = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By scrollButton = By.cssSelector(".Home_FinishButton__1_cWm");
    private final By inputNameField = By.cssSelector("input[placeholder='* Имя']");
    private final By inputSurNameField = By.cssSelector("input[placeholder='* Фамилия']");
    private final By inputAddressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private final By allStationsField = By.cssSelector(".select-search");
    private final By nameStation = By.cssSelector(".select-search__input");
    private final By stationButton = By.cssSelector("ul.select-search__options li.select-search__row[data-index='0'] button");
    private final By inputPhoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonFurther = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private final By inputDateField = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private final By dropDownArrow = By.cssSelector(".Dropdown-arrow");
    private final By dropDownList = By.cssSelector(".Dropdown-option");
    private final By inputCheckboxFieldBlack = By.cssSelector("input[id='black']");
    private final By inputCheckboxFieldGrey = By.cssSelector("input[id='grey']");
    private final By inputMessageField = By.cssSelector("input[placeholder='Комментарий для курьера']");
    private final By clickButtonOrder = By.cssSelector(".Order_Buttons__1xGrp button:nth-child(2)");
    private final By ConfirmClickButtonOrder = By.cssSelector(".Order_Modal__YZ-d3 .Order_Buttons__1xGrp button:nth-child(2)");
    private final By statusButton = By.cssSelector(".Order_NextButton__1_rCA button:nth-child(1)");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void startTypeOfTest(int i){
        System.out.println("-------------------------------");
        if(i==0){
            System.out.println("Проверка заказ самоката - верхняя кнопка \"Заказать\"");
        }
        if(i==1){
            System.out.println("Проверка заказ самоката - нижняя кнопка \"Заказать\"");
        }else{ System.out.println("");}
    };
    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    public void clickTopButtonOrder(){
        driver.findElement(buttonOrderTop).click();
    }

    public void clickBottomButtonOrder(){
        driver.findElement(buttonOrderBottom).click();
    }

    public void scrollToButton() {
        WebElement element = driver.findElement(scrollButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    public  void openPageOrder(){
        driver.get(EnvConfig.ORDER_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
    }
    public void inputUserDataInFields(){
        inputName("Иван");
        inputSurname("Малышев");
        inputAddress("ул. Ленина 25");
        inputStation("Сокольники");
        inputPhone("79091234564");


    }
    public void inputName(String name){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputNameField));
        Assert.assertTrue(driver.findElement(inputNameField).isDisplayed());
        driver.findElement(inputNameField).sendKeys(name);
    }

    public void inputSurname(String surname){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputSurNameField));
        Assert.assertTrue(driver.findElement(inputSurNameField).isDisplayed());
        driver.findElement(inputSurNameField).sendKeys(surname);
    }

    public void inputAddress(String address){

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputAddressField));
        Assert.assertTrue(driver.findElement(inputAddressField).isDisplayed());
        driver.findElement(inputAddressField).sendKeys(address);
    }
    public void inputStation(String station){
        driver.findElement(allStationsField).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allStationsField));
        Assert.assertTrue(driver.findElement(nameStation).isDisplayed());
        driver.findElement(nameStation).sendKeys(station);
        driver.findElement(stationButton).click();


    }
    public void inputPhone(String phoneNumber){

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputPhoneField));
        Assert.assertTrue(driver.findElement(inputPhoneField).isDisplayed());
        driver.findElement(inputPhoneField).sendKeys(phoneNumber);
    }

    public void clickButtonFurther(){
        driver.findElement(buttonFurther).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
    }
    public void inputRentDataInFields(){
        inputDate();
        rentalPeriod("двое суток");
        colorScooter("grey");
        commentsForCourier("без комментариев");

    }
    public void inputDate(){

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputDateField));
        Assert.assertTrue(driver.findElement(inputDateField).isDisplayed());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate  dateMonthYear = LocalDate.now();
        String onlyDate = String.format("%02d", dateMonthYear.getDayOfMonth());
        driver.findElement(inputDateField).sendKeys(dateMonthYear.format(formatter));
        String classDate=".react-datepicker__day.react-datepicker__day--0"+onlyDate+".react-datepicker__day--selected.react-datepicker__day--today";
        driver.findElement(By.cssSelector(classDate)).click();
    }

    public void rentalPeriod(String Period){
        driver.findElement(dropDownArrow).click();
        List<WebElement> elements =  driver.findElements(dropDownList);
        for (WebElement e : elements) {
            String elem = e.getText();
            if(Period.equalsIgnoreCase(elem)){
                e.click();
                break;
            }
        }

    }
    public void colorScooter(String color) {
       if(color == "black"){
           driver.findElement(inputCheckboxFieldBlack).click();
       }
        if(color == "grey"){
            driver.findElement(inputCheckboxFieldGrey).click();
        }

    }
    public void commentsForCourier(String message){

        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(inputMessageField));
        Assert.assertTrue(driver.findElement(inputMessageField).isDisplayed());
        driver.findElement(inputMessageField).sendKeys(message);

    }
    public void clickButtonOrder()
    {
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
       driver.findElement(clickButtonOrder).click();
    }
    public void confirmClickButtonOrder()
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
        //Order_Modal__YZ-d3
        driver.findElement(ConfirmClickButtonOrder).click();
    }

    public void confirmOrder(){

        if(driver.findElement(statusButton).isEnabled()){
            System.out.println("-------------------------------");
            System.out.println("Тест пройден");
        }
        else {
            System.out.println("-------------------------------");
            System.out.println("Тест провален");
        }


    }
}

