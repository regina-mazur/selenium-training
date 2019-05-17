package selenium.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.AdminPage;
import selenium.pages.CartPage;
import selenium.pages.HomePage;
import selenium.pages.ProductPage;
import util.PropertyLoader;

public class Application {
    private EventFiringWebDriver driver;
    private WebDriverWait webDriverWait;

    public HomePage homePage;
    public ProductPage productPage;
    public CartPage cartPage;
    public AdminPage adminPage;

    public static class MyListener extends AbstractWebDriverEventListener{
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by);
            super.beforeFindBy(by, element, driver);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
            super.afterFindBy(by, element, driver);
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.println(throwable);
            super.onException(throwable, driver);
        }
    }

    public Application(){
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());

        webDriverWait = new WebDriverWait(driver, 10);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        adminPage = new AdminPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void loginAsAdmin(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        driver.findElement(By.name("username")).sendKeys(PropertyLoader.loadProperty("admin.username"));
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.name("login")).click();
    }
    public void loginAs(String email, String password){
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void getBrowserLogs(){
        driver.manage().logs().get("browser").getAll().forEach(System.out::println);
    }
}
