package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class OpenPageTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void openPageTest(){
        driver.get("https://www.seleniumhq.org");
        driver.findElement(By.id("menu_documentation")).click();
        wait.until(titleIs("Selenium Documentation — Selenium Documentation"));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
