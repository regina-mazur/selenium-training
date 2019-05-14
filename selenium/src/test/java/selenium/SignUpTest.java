package selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import selenium.model.UserData;
import util.PropertyLoader;

import java.util.UUID;

public class SignUpTest extends BaseTest {
    private UserData testUser;

    @Before
    public void setUserData() {
        testUser = new UserData();
        testUser.setFirstName("Firstname");
        testUser.setLastName("Lastname");
        testUser.setAddress1("West Wind Glendale 9");
        testUser.setPostcode("85301");
        testUser.setCity("City");
        testUser.setCountry("US");
        testUser.setState("AZ");
        testUser.setEmail(UUID.randomUUID().toString().substring(0, 9) + "test@test.com");
        testUser.setPhone("+16239399715");
        testUser.setPassword("123123");

    }

    @Test
    public void signUpTest() {
        driver.get(PropertyLoader.loadProperty("homepage.url"));
        driver.findElement(By.linkText("New customers click here")).click();
        fillSignUpFields(testUser);
        driver.findElement(By.name("create_account")).click();
        driver.findElement(By.linkText("Logout")).click();
        loginAs(testUser.getEmail(), testUser.getPassword());
        Assert.assertTrue(driver.findElement(By.cssSelector("#notices .success")).isDisplayed());
    }

    private void fillSignUpFields(UserData user) {
        driver.findElement(By.name("firstname")).sendKeys(user.getFirstName());
        driver.findElement(By.name("lastname")).sendKeys(user.getLastName());
        driver.findElement(By.name("address1")).sendKeys(user.getAddress1());
        driver.findElement(By.name("postcode")).clear();
        driver.findElement(By.name("postcode")).sendKeys(user.getPostcode());
        driver.findElement(By.name("city")).sendKeys(user.getCity());

        new Select(driver.findElement(By.name("country_code"))).selectByValue(testUser.getCountry());
        new Select(driver.findElement(By.xpath("//select[@name='zone_code']"))).selectByValue(testUser.getState());

        driver.findElement(By.name("email")).sendKeys(user.getEmail());
        driver.findElement(By.name("phone")).sendKeys(user.getPhone());
        driver.findElement(By.name("password")).sendKeys(user.getPassword());
        driver.findElement(By.name("confirmed_password")).sendKeys(user.getPassword());
    }
}
