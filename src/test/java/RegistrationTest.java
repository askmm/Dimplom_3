import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburger.testui.api.ApiMethods;
import ru.stellarburger.testui.pages.MainPage;
import ru.stellarburger.testui.user.RandomUser;

import static org.junit.Assert.*;
import static ru.stellarburger.testui.Config.*;

public class RegistrationTest extends BaseTest {

    private RandomUser user;
    private MainPage mainPage;

    @Before
    public void init(){
        mainPage = new MainPage(driver);
        user = new RandomUser();
        driver.get(BASE_URI);
    }

    @Test
    public void redirectedAfterRegistrationSuccessfully(){
        mainPage.profileButtonClickBeforeLogin()
                .clickRegisterLink()
                .register(user)
                .waitWhilePageLoading();
        boolean redirected = driver.getCurrentUrl().equals(LOGIN_URI);
        assertTrue(redirected);
    }

    @Test
    public void userCreatedSuccessfully(){
        mainPage.profileButtonClickBeforeLogin()
                .clickRegisterLink()
                .register(user)
                .waitWhilePageLoading()
                .login(user);
        jwt = ((ChromeDriver) driver).getLocalStorage().getItem("accessToken");
        assertNotNull(jwt);
    }

    @Test
    public void shortPasswordErrorShown(){
        boolean errorIsShown = mainPage.profileButtonClickBeforeLogin()
                .clickRegisterLink()
                .registerFail(user.cutPassword())
                .passwordErrorShown();
        assertTrue(errorIsShown);
    }

    @Test
    public void userWithShortPasswordCreationFail(){
        mainPage.profileButtonClickBeforeLogin()
                .clickRegisterLink()
                .register(user.cutPassword());

        ApiMethods.login(user);
        jwt = user.getAccessToken();
        assertNull(jwt);
    }

    @After
    @Step("Try to fill JWT in case user created")
    public void fillJWT() {
        if (jwt == null || jwt.isEmpty()) {
            ApiMethods.login(user);
            jwt = user.getAccessToken();
        }
    }
}
