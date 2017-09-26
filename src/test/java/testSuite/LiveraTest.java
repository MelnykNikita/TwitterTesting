package testSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Никита Мельник on 25.09.2017.
 */

public class LiveraTest extends BaseTest {

    WebDriver driver;
    JavascriptExecutor jsexecutor;
    WebElement product;
    WebElement brandName;

    private final String SITE = "https://www.livera.nl";

    @Test
    public void verifyAbsenceOfProductOnPage() throws InterruptedException {
        driver = getDriver();
        jsexecutor = (JavascriptExecutor) getDriver();
        driver.get(SITE);
        Thread.sleep(1000);
        List<WebElement> catalogLinks = driver.findElements(By.cssSelector(".elSubNavItemLinks-item-url"));
        int i = 0;
        for (WebElement e: catalogLinks) {
            jsexecutor.executeScript("document.querySelectorAll(\".elSubNavItemLinks-item-url\")[" + i + "].click();");
            Thread.sleep(1000);
            try {
                product = driver.findElement(By.cssSelector(".elProductItem"));
            } catch (Exception exception) {
                System.out.println("PAGE without PRODUCT: " + driver.getCurrentUrl());
            }
            i++;
        }
    }

    @Test
    public void verifyAbsenceOfBrandFilter() throws InterruptedException {
        driver = getDriver();
        jsexecutor = (JavascriptExecutor) getDriver();
        driver.get(SITE);
        Thread.sleep(1000);
        List<WebElement> catalogLinks = driver.findElements(By.cssSelector(".elSubNavItemLinks-item-url"));
        int i = 0;
        for (WebElement e: catalogLinks) {
            jsexecutor.executeScript("document.querySelectorAll(\".elSubNavItemLinks-item-url\")[" + i + "].click();");
            Thread.sleep(1000);
            try {
                product = driver.findElement(By.cssSelector(".elProductItem"));
            } catch (Exception exception) {

            }
            if (product != null) {
                try {
                    brandName = driver.findElement(By.xpath("//span[.='Merk']"));
                } catch (Exception exception) {
                    System.out.println("PAGE without Brand-Filter: " + driver.getCurrentUrl());
                }
            }
            i++;
        }
    }
}
