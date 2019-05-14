package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CampaignProductTest extends BaseTest {
    @Test
    public void campaignProductTest(){
        driver.get(PropertyLoader.loadProperty("homepage.url"));
        HashMap<String, String> homePageValues = getInfoFromHomePage();
        driver.findElement(By.cssSelector("#box-campaigns li")).click();
        HashMap<String, String> productPageValues = getInfoFromProductPage();

        Assert.assertEquals(homePageValues.get("name"), productPageValues.get("name"));
        Assert.assertEquals(homePageValues.get("regularPrice"), productPageValues.get("regularPrice"));
        Assert.assertEquals(homePageValues.get("campaignPrice"), productPageValues.get("campaignPrice"));

        //homePage
        Assert.assertEquals(homePageValues.get("textDecoration"), "line-through");
        List<Integer> regularPriceHomepageColor = getColor(homePageValues.get("regularPriceColor"));
        Assert.assertEquals(regularPriceHomepageColor.get(0), regularPriceHomepageColor.get(1));
        Assert.assertEquals(regularPriceHomepageColor.get(1), regularPriceHomepageColor.get(2));

        List<Integer> campaignPriceHomepageColor = getColor(homePageValues.get("campaignPriceColor"));
        Assert.assertTrue(campaignPriceHomepageColor.get(1) == 0);
        Assert.assertTrue(campaignPriceHomepageColor.get(2) == 0);
        Assert.assertTrue(getFontSize(homePageValues.get("regularPriceFontSize")) <
                getFontSize(homePageValues.get("campaignPriceFontSize")));


        //productPage
        Assert.assertEquals(productPageValues.get("textDecoration"), "line-through");
        List<Integer> regularPriceProductColor = getColor(productPageValues.get("regularPriceColor"));
        Assert.assertEquals(regularPriceProductColor.get(0), regularPriceProductColor.get(1));
        Assert.assertEquals(regularPriceProductColor.get(1), regularPriceProductColor.get(2));

        List<Integer> campaignPriceProductColor = getColor(productPageValues.get("campaignPriceColor"));
        Assert.assertTrue(campaignPriceProductColor.get(1) == 0);
        Assert.assertTrue(campaignPriceProductColor.get(2) == 0);
        Assert.assertTrue(getFontSize(productPageValues.get("regularPriceFontSize")) <
                getFontSize(productPageValues.get("campaignPriceFontSize")));
    }

    private HashMap getInfoFromHomePage(){
        WebElement homePageItem = driver.findElement(By.cssSelector("#box-campaigns li"));

        HashMap<String, String> homePageValues = new HashMap<>();
        homePageValues.put("name", homePageItem.findElement(By.cssSelector(".name")).getText());

        WebElement regularPrice = homePageItem.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPrice = homePageItem.findElement(By.cssSelector("strong.campaign-price"));

        homePageValues.put("textDecoration", regularPrice.getCssValue("text-decoration"));
        homePageValues.put("regularPriceColor", regularPrice.getCssValue("color"));
        homePageValues.put("regularPriceFontSize", regularPrice.getCssValue("font-size"));

        homePageValues.put("campaignPriceColor", campaignPrice.getCssValue("color"));
        homePageValues.put("campaignPriceFontSize", campaignPrice.getCssValue("font-size"));

        homePageValues.put("regularPrice", regularPrice.getText());
        homePageValues.put("campaignPrice", campaignPrice.getText());

        return homePageValues;
    }

    private HashMap getInfoFromProductPage(){
        WebElement productPageItem = driver.findElement(By.cssSelector("#box-product"));

        HashMap<String, String> productPageValues = new HashMap<>();

        productPageValues.put("name", productPageItem.findElement(By.cssSelector(".title")).getText());

        WebElement regularPrice = productPageItem.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPrice = productPageItem.findElement(By.cssSelector("strong.campaign-price"));

        productPageValues.put("textDecoration", regularPrice.getCssValue("text-decoration"));
        productPageValues.put("regularPriceColor", regularPrice.getCssValue("color"));
        productPageValues.put("regularPriceFontSize", regularPrice.getCssValue("font-size"));

        productPageValues.put("campaignPriceColor", campaignPrice.getCssValue("color"));
        productPageValues.put("campaignPriceFontSize", campaignPrice.getCssValue("font-size"));

        productPageValues.put("regularPrice", regularPrice.getText());
        productPageValues.put("campaignPrice", campaignPrice.getText());

        return productPageValues;
    }
    private Double getFontSize(String fontSize){
        return Double.parseDouble(fontSize.substring(0, fontSize.length() - 2));
    }
    private List<Integer> getColor(String rgbColor){
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(rgbColor);
        List<Integer> colors = new ArrayList<>();
        while (matcher.find()) {
            colors.add(Integer.parseInt(matcher.group()));
        }
        return colors;
    }
}
