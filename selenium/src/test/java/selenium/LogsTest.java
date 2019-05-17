package selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import util.PropertyLoader;

public class LogsTest extends BaseTest {
    @Test
    public void checkBrowserLogs(){
        app.loginAsAdmin();
        app.adminPage.openCategory();
        for(int i =1; i<= app.adminPage.products.size(); i++){
            app.adminPage.openProduct(i);
            app.getBrowserLogs();
            app.adminPage.cancelButton.click();
        }
    }
}
