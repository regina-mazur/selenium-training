import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
