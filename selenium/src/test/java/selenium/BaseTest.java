package selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.PropertyLoader;

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

    public void loginAsAdmin(){
        driver.findElement(By.name("username")).sendKeys(PropertyLoader.loadProperty("admin.username"));
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.name("login")).click();
    }
}
