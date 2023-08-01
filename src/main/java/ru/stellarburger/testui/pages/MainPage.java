package ru.stellarburger.testui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.stellarburger.testui.Config.*;

public class MainPage extends StellarBurgerPage {
    public static final By profileButton = By.xpath(".//p[text() = 'Личный Кабинет']");
    public static final By goToAccountButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    public static final By loginLink = By.xpath(".//a[@href = '/login' and text() = 'Войти']");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Go to profile from main page")
    public ProfilePage profileButtonClickAfterLogin(){
        profileButtonClick();
        return new ProfilePage(driver);
    }

    @Step("Go to login from main page")
    public LoginPage profileButtonClickBeforeLogin(){
        profileButtonClick();
        return new LoginPage(driver);
    }

    @Step("Click profile button on main page")
    private void profileButtonClick(){
        driver.findElement(profileButton).click();
    }


    public MainPage waitWhilePageLoading(String uri){
        try {
            new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.urlToBe(uri));
        } catch (TimeoutException e) {
            throw  e;
        }
        return this;
    }

}
