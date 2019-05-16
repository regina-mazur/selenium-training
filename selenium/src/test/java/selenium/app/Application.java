package selenium.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.CartPage;
import selenium.pages.HomePage;
import selenium.pages.ProductPage;
import util.PropertyLoader;

public class Application {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public HomePage homePage;
    public ProductPage productPage;
    public CartPage cartPage;

    public Application(){
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void loginAsAdmin(){
        driver.findElement(By.name("username")).sendKeys(PropertyLoader.loadProperty("admin.username"));
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.name("login")).click();
    }
    public void loginAs(String email, String password){
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
