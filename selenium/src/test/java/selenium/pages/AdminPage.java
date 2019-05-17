package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminPage extends BasePage {
    public AdminPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[contains(@name,'products')]/../../td[3]/a")
    public List<WebElement> products;

    @FindBy(css = "button[name='cancel']")
    public WebElement cancelButton;

    public void openCategory(){
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Rubber Ducks')]")).click();
    }

    public void openProduct(int productRow) {
            driver.findElement(By.xpath("(//input[contains(@name,'products')]/../../td[3]/a)[" + productRow + "]")).click();
    }
}
