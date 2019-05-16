package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{
    public CartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "a.content strong")
    WebElement openCartLink;

    @FindBy(name = "remove_cart_item")
    WebElement removeButton;


    public void deleteProductsFromCart(Integer amount){
        openCartLink.click();
        for (Integer i=0; i<amount; i++){
            webDriverWait.until(ExpectedConditions.elementToBeClickable(removeButton));
            removeButton.click();
            webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"),amount-i));
        }
    }
}
