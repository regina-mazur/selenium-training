package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    @Before
    public void start(){
        //driver = new ChromeDriver();

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");
        driver = new FirefoxDriver(firefoxOptions);
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
