package selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.model.ProductData;
import util.PropertyLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddingProductTest extends BaseTest {
    private static final String PICTURE_PATH = "./src/test/resources/A097Rr4CEAALo8U.jpg";
    private static ProductData testData;

    @Before
    public void setProductData(){
        testData = new ProductData();
        testData.setName(UUID.randomUUID().toString().substring(0,5));
        testData.setCode("Product Code");
        List<String> categories = new ArrayList<>();
        categories.add("Subcategory");
        testData.setCategories(categories);
        testData.setQuantity(12);
        testData.setImage(new File(PICTURE_PATH));
        testData.setDateTo("12.12.2019");
        testData.setDateFrom("31.12.2019");
        testData.setManufacturer("1");
        testData.setKeywords("test test");
        testData.setShortDescription("product short description");
        testData.setHeadTitle("Test product title");
        testData.setPurchasePrice(1200);
        testData.setCurrency("USD");
    }
    @Test
    public void addingProductTest(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        app.loginAsAdmin();
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();
        fillProductFields(testData);
        driver.findElement(By.name("save")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//table[@class='dataTable']//a[text()='" + testData.getName() +"']")).isDisplayed());
    }

    private void fillProductFields(ProductData newProduct){
        //General
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]")).click();
        driver.findElement(By.name("name[en]")).sendKeys(newProduct.getName());
        driver.findElement(By.name("code")).sendKeys(newProduct.getCode());
        driver.findElement(By.cssSelector("input[data-name='Root']")).click();
        for (String category: newProduct.getCategories()
             ) {
            driver.findElement(By.cssSelector("input[data-name='" + category +"']")).click();
        }
        driver.findElement(By.name("quantity")).sendKeys(newProduct.getQuantity().toString());
        driver.findElement(By.name("new_images[]")).sendKeys(testData.getImage());
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("document.querySelector(\"input[name='date_valid_from']\").value=\"" + newProduct.getDateFrom() + "\"");
        je.executeScript("document.querySelector(\"input[name='date_valid_to']\").value=\"" + newProduct.getDateTo()+ "\"");

        //Info
        driver.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("manufacturer_id")));
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByValue(newProduct.getManufacturer());
        driver.findElement(By.name("keywords")).sendKeys(newProduct.getKeywords());
        driver.findElement(By.name("head_title[en]")).sendKeys(newProduct.getHeadTitle());

        //Prices
        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("purchase_price")));
        driver.findElement(By.name("purchase_price")).sendKeys(newProduct.getPurchasePrice().toString());
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue(newProduct.getCurrency());

    }
}
