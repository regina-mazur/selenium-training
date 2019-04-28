import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminLoginTest extends BaseTest{
    private static String adminUsername = "admin";
    private static String adminPassword = "admin";
    @Test
    public void adminLoginTest(){
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys(adminUsername);
        driver.findElement(By.name("password")).sendKeys(adminPassword);
        driver.findElement(By.name("login")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("i.fa-sign-out")));
    }

}
