package ru.yandex.practicum.pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public String getFaqQuestion(int i){
        List<WebElement> elements = driver.findElements(accordionItem);
        int j=0;
        String FaqQuestion = "";
        for (WebElement e : elements) {

            openAccordeonBody(driver, e, j);
            if(j==i)
            {
                new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(errorTitle));
                Assert.assertTrue(driver.findElement(errorTitle).isDisplayed());

                FaqQuestion =  e.findElement(errorTitle).getText();
            }

            closeAccordeonBody(driver, e, j);
            j++;

        }
        return FaqQuestion;
    }

    public String getFaqAnswer(int i){
        List<WebElement> elements = driver.findElements(accordionItem);
        int j=0;
        String FaqAnswer = "";
        for (WebElement e : elements) {

            openAccordeonBody(driver, e, j);
            if(j==i)
            {
               String htmlString = driver.findElement(By.cssSelector("div[id='accordion__panel-"+j+"']")).getAttribute("outerHTML");
                Pattern pattern = Pattern.compile("<p>(.*?)</p>");
                Matcher matcher = pattern.matcher(htmlString);
                if (matcher.find()) {
                    FaqAnswer = matcher.group(1);
                }

            }
                closeAccordeonBody(driver, e, j);
            j++;

        }
        return FaqAnswer;
    }




}

