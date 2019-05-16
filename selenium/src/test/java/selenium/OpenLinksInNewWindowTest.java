package selenium;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import util.PropertyLoader;

import java.util.List;
import java.util.Set;

public class OpenLinksInNewWindowTest extends BaseTest {
    private static final String LINK_ICON="i.fa-external-link";
    @Test
    public void openLinksInNewWindow(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        app.loginAsAdmin();
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        driver.findElement(By.cssSelector("i.fa-pencil")).click();
        openLinks();
    }
    private void openLinks(){
        String mainWindow = driver.getWindowHandle();
        List<WebElement> linksOnPage = driver.findElements(By.cssSelector(LINK_ICON));
        for (WebElement link : linksOnPage
             ) {
            Set<String> existingWindows = driver.getWindowHandles();
            link.click();
            String newWindow = webDriverWait.until(windowOpened(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }
    private ExpectedCondition<String> windowOpened(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }
}
