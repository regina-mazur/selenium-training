package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import util.PropertyLoader;

public class OpenEachMenuItemTest extends BaseTest {
    @Test
    public void visitEachMenuItem(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        driver.findElement(By.name("username")).sendKeys(PropertyLoader.loadProperty("admin.username"));
        driver.findElement(By.name("password")).sendKeys(PropertyLoader.loadProperty("admin.password"));
        driver.findElement(By.name("login")).click();
        checkEachMenuItem();
    }

    private void checkEachMenuItem(){
        Integer menuItemsCount = driver.findElements(By.id("app-")).size();

        for (Integer menuItem = 0; menuItem < menuItemsCount; menuItem++) {
            driver.findElements(By.id("app-")).get(menuItem).click();

            Integer subMenuItemsCount = driver.findElements(By.cssSelector(".docs li")).size();
            for(Integer subMenuItem = 0; subMenuItem < subMenuItemsCount; subMenuItem++){
                driver.findElements(By.cssSelector(".docs li")).get(subMenuItem).click();
                driver.findElement(By.tagName("h1"));
            }
        }
        driver.findElement(By.tagName("h1"));
    }
}
