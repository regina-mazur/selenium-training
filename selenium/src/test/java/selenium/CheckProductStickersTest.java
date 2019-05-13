package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.List;

public class CheckProductStickersTest extends BaseTest {
    @Test
    public void checkProductStickersTest(){
        driver.get(PropertyLoader.loadProperty("homepage.url"));

        List<WebElement> productsList = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
        for(WebElement product : productsList){
            Assert.assertEquals(product.findElements(By.xpath(".//div[contains(@class,'sticker')]")).size(),1);
        }
    }

}
