import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stellarburger.testui.api.ApiMethods;
import ru.stellarburger.testui.pages.LoginPage;
import ru.stellarburger.testui.pages.MainPage;
import ru.stellarburger.testui.user.RandomUser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.stellarburger.testui.Config.*;

public class ProfileTest extends BaseTest{

    private RandomUser user;
    private MainPage mainPage;

    @Before
    public void createUserAndLogin(){
        user = new RandomUser();
        ApiMethods.createUser(user);
        driver.get(LOGIN_URI);
        mainPage = new LoginPage(driver).login(user);
        jwt = ((ChromeDriver) driver).getLocalStorage().getItem("accessToken");
    }

    @Test
    public void checkProfileLinkClickSuccessful(){
        mainPage
                .profileButtonClickAfterLogin()
                .waitWhilePageLoading();
        assertThat(driver.getCurrentUrl(), equalTo(PROFILE_URI));
    }

    @Test
    public void checkLinkFromProfileToConstructorClickSuccessful(){
        mainPage
                .profileButtonClickAfterLogin()
                .clickConstructor()
                .waitWhilePageLoading(BASE_URI);
        assertThat(driver.getCurrentUrl(), equalTo(BASE_URI));
    }

    @Test
    public void checkLinkFromProfileToMainPageClickSuccessful(){
        mainPage
                .profileButtonClickAfterLogin()
                .clickLogoLink()
                .waitWhilePageLoading(BASE_URI);
        assertThat(driver.getCurrentUrl(), equalTo(BASE_URI));
    }

    @Test
    public void checkProfileExitClickSuccessful(){
        mainPage
                .profileButtonClickAfterLogin()
                .waitWhilePageLoading()
                .clickExitButton()
                .waitWhilePageLoading();
        assertThat(driver.getCurrentUrl(), equalTo(LOGIN_URI));
        String accessToken = ((ChromeDriver)driver).getLocalStorage().getItem("accessToken");
        assertThat(accessToken, nullValue());
    }

}
