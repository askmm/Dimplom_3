package ru.stellarburger.testui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stellarburger.testui.user.RandomUser;

public class RegisterPage extends StellarBurgerPage{
    public static final By nameField = By.xpath(".//input[@name='name']");
    public static final By emailField = By.xpath(".//label[text() = 'Email']/../input");
    public static final By passwordField = By.xpath(".//input[@name='Пароль']");
    public static final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    public static final By shortPasswordError = By.xpath(".//p[@class='input__error text_type_main-default' and text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Fill user data on register page")
    private void fillUserData(RandomUser user){
        driver.findElement(nameField).sendKeys(user.getEmail());
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Register")
    public LoginPage register(RandomUser user){
        fillUserData(user);
        driver.findElement(registerButton).click();
        return new LoginPage(driver);
    }

    @Step("Try to register")
    public RegisterPage registerFail(RandomUser user){
        fillUserData(user);
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Check if short password error is shown")
    public boolean passwordErrorShown(){
        return driver.findElement(shortPasswordError).isDisplayed();
    }


}
