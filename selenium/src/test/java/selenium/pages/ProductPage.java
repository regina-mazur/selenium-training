package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name= "options[Size]")
    Select productSize;

    @FindBy(name = "add_cart_product")
    WebElement addToCartButton;

    @FindBy(css = ".general-0")
    WebElement homeIcon;

    public void addProductsToCart(Integer amount){
        for(Integer i=1; i<=amount; i++){
            driver.findElement(By.xpath("//li[contains(@class,'product')]["+ i +"]")).click();
            if(driver.findElements(By.name("options[Size]")).size()!=0){ //Yellow duck
                productSize.selectByValue("Small");
            }
            addToCartButton.click();
            webDriverWait.until(ExpectedConditions.textToBe(By.cssSelector(".quantity"), i.toString()));
            homeIcon.click();
        }
    }
}
