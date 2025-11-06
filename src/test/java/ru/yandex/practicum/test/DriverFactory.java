package ru.yandex.practicum.test;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.pages.util.EnvConfig;


import java.time.Duration;

public class DriverFactory extends ExternalResource {
    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;

    public void initDriver(){
        if("firefox".equals(System.getProperty("browser"))){
            startFirefox();
        }else{
          startChrome();


        }
    }

    private void startChrome() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
        driver.manage().window().fullscreen();
    }

    private void startFirefox() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(EnvConfig.INPLICITY_TIMEOUT));
        driver.manage().window().maximize();
    }

    @Override
    protected void before(){
        initDriver();
    }

    @Override
    protected void after(){
        driver.quit();
    }
}
