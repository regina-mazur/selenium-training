package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.app.Application;

public class BaseTest {
    Application app;

    @Before
    public void start(){
        app = new Application();
    }

    @After
    public void stop(){
        app.quit();
    }
}
