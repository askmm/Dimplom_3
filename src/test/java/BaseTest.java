import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.stellarburger.testui.pages.MainPage;

import static ru.stellarburger.testui.WebDriverFactory.getWebDriver;
import static ru.stellarburger.testui.api.ApiMethods.deleteUser;

public class BaseTest {
    WebDriver driver;
    String jwt;

    @Before
    public void setUp(){
        driver = getWebDriver();
    }

    @After
    public void cleanUp(){
        driver.quit();
        if (jwt != null && !jwt.isEmpty()) {
            deleteUser(jwt);
        }
    }
}
