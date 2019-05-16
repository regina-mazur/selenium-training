package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import util.PropertyLoader;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage open(){
        driver.get(PropertyLoader.loadProperty("homepage.url"));
        return this;
    }

}
