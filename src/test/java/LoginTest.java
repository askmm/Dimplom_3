import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburger.testui.pages.MainPage;
import ru.stellarburger.testui.pages.StellarBurgerPage;
import ru.stellarburger.testui.user.RandomUser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stellarburger.testui.Config.*;
import static ru.stellarburger.testui.api.ApiMethods.createUser;

@RunWith(Parameterized.class)
public class LoginTest extends BaseTest{

    private String startPage;
    private By buttonLocator;

    public LoginTest(String startPage, By buttonLocator) {
        this.startPage = startPage;
        this.buttonLocator = buttonLocator;
    }

    @Parameterized.Parameters
    public static Object[][] getParams(){
        return new Object[][] {
                {BASE_URI, MainPage.profileButton},
                {BASE_URI, MainPage.goToAccountButton},
                {RESTORE_PASSWORD_URI, MainPage.loginLink},
                {REGISTER_URI, MainPage.loginLink},
        };
    }

    @Test
    public void testLoginProfileButtonSuccess(){
        RandomUser user = new RandomUser();
        createUser(user);
        StellarBurgerPage page = new StellarBurgerPage(driver);
        page
                .clickLoginButton(startPage, buttonLocator)
                .login(user);
        jwt = ((ChromeDriver) driver).getLocalStorage().getItem("accessToken");
        assertThat(jwt, notNullValue());
    }

}
