package selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import util.PropertyLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortingTest extends BaseTest{
    @Test
    public void countriesSortingTest(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        app.loginAsAdmin();
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        Assert.assertTrue(checkCountriesSorting());
        Assert.assertTrue(checkGeoZonesInsideCountry());

    }
    @Test
    public void geoZonesSortingTest(){
        driver.get(PropertyLoader.loadProperty("admin.url"));
        app.loginAsAdmin();
        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
        Integer countriesCount = driver.findElements(By.cssSelector(".dataTable tr.row")).size();

        for (Integer countryNumber = 1; countryNumber<=countriesCount; countryNumber++){
            driver.findElement(By.xpath(".//tr[@class='row']["+ countryNumber + "]/td[3]/a")).click();
            Assert.assertTrue(checkZonesSorting());
            driver.navigate().back();
        }
    }

    private Boolean checkCountriesSorting(){
        List<WebElement> elementsList = driver.findElements(By.xpath("//tr/td[5]/a"));
        List<String> countriesNames = new ArrayList<>();
        for (WebElement countryLink : elementsList
        ) {
            countriesNames.add(countryLink.getText());
        }
        return countriesNames.stream().sorted().collect(Collectors.toList()).equals(countriesNames);
    }

    private boolean checkGeoZonesInsideCountry(){
        boolean geoZonesEqual = true;
        List<String> countriesWithGeoZonesNames = new ArrayList<>();
        List<WebElement> countryElements  = driver.findElements(By.xpath("//tr/td[6][not(contains(text(), '0'))]/../td[5]/a"));
        for (WebElement country : countryElements
        ) {
            countriesWithGeoZonesNames.add(country.getText());
        }
        for (String countryName : countriesWithGeoZonesNames
        ) {
            driver.findElement(By.xpath("//a[contains(text(),'" + countryName + "')]")).click();
            List<WebElement> geoZoneElements = driver.findElements(By.xpath("//td[3]/input[contains(@name, 'zones')]"));
            List<String> geoZoneNames = new ArrayList<>();
            for (WebElement geoZone : geoZoneElements
                 ) {
                geoZoneNames.add(geoZone.getAttribute("value"));
            }
            if (!geoZoneNames.stream().sorted().collect(Collectors.toList()).equals(geoZoneNames)) {
                geoZonesEqual = false;
                break;
            }
            driver.navigate().back();
        }
        return geoZonesEqual;
    }

    private boolean checkZonesSorting(){
        List<WebElement> geoZonesElements = driver.findElements(By.cssSelector("table#table-zones td:nth-child(3) option[selected]"));
        List<String> geoZoneNames = new ArrayList<>();
        for (WebElement geoZone: geoZonesElements
        ) {
            geoZoneNames.add(geoZone.getText());
        }
        return geoZoneNames.stream().sorted().collect(Collectors.toList()).equals(geoZoneNames);
    }
}
