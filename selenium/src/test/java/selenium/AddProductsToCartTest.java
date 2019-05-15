package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import util.PropertyLoader;

public class AddProductsToCartTest extends BaseTest {
    private static final Integer PRODUCT_AMOUNT = 3;
    @Test
    public void addProductsToCartTest(){
        driver.get(PropertyLoader.loadProperty("homepage.url"));
        addProductsToCart(PRODUCT_AMOUNT);
        driver.findElement(By.cssSelector("a.content strong")).click();
        deleteProductsFromCart(PRODUCT_AMOUNT);
    }

    private void addProductsToCart(Integer amount){
        for(Integer i=1; i<=amount; i++){
            driver.findElement(By.xpath("//li[contains(@class,'product')]["+ i +"]")).click();
            if(driver.findElements(By.name("options[Size]")).size()!=0){ //Yellow duck
                new Select(driver.findElement(By.name("options[Size]"))).selectByValue("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            webDriverWait.until(ExpectedConditions.textToBe(By.cssSelector(".quantity"), i.toString()));
            driver.findElement(By.cssSelector(".general-0")).click();
        }

    }
    private void deleteProductsFromCart(Integer amount){
        for (Integer i=0; i<amount; i++){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("remove_cart_item")));
            driver.findElement(By.name("remove_cart_item")).click();
            webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"),amount-i));
        }
    }
}
