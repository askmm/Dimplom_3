import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.stellarburger.testui.pages.ConstructorPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static ru.stellarburger.testui.Config.BASE_URI;

@RunWith(Parameterized.class)
public class ChangeCategoryTest extends BaseTest{
    private String tabName;
    private String previousTabName;

    public ChangeCategoryTest(String previousTabName, String tabName) {
        this.tabName = tabName;
        this.previousTabName = previousTabName;
    }

    @Parameterized.Parameters
    public static Object[][] getParams() {
        return new Object[][]{
                {"Булки", "Начинки"},
                {"Булки", "Соусы"},
                {"Начинки", "Булки"},
                {"Соусы", "Булки"},
        };
    }

    @Test
    public void ChosenCategoryOnTop(){
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(BASE_URI);
        ConstructorPage constructor = new ConstructorPage(driver);
        constructor
                .chooseTab(previousTabName)
                .chooseTab(tabName);
        boolean isSelected = constructor.tabIsSelected(tabName);
        assertTrue(isSelected);
    }
}
