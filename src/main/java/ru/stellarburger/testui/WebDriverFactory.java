package ru.stellarburger.testui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class WebDriverFactory {
    public static final String CHROME_PROPERTY = "chrome";
    public static final String YANDEX_PROPERTY = "yabrowser";
    public static WebDriver getWebDriver(){
        String browser = System.getProperty("WebDriverName");
        WebDriver driver;
        switch (browser) {
            case (CHROME_PROPERTY):
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver = new ChromeDriver();
                break;
            case (YANDEX_PROPERTY):
                WebDriverManager.chromedriver().setup();
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("src/main/resources/yandexdriver"))
                        .build();
                driver = new ChromeDriver(service);
                break;
            default:
                throw new WebDriverException();
        }
        return driver;
    }
}
