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
    WebElement categorie;
    WebElement type;

    private final String SITE_LIVERA = "https://www.livera.nl";
    private final String SITE_EL = "https://editionlingerie.de";

    @Test
    public void verifyAbsenceOfProductOnPage() throws InterruptedException {
        driver = getDriver();
        jsexecutor = (JavascriptExecutor) getDriver();
        driver.get(SITE_LIVERA);
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
        driver.get(SITE_LIVERA);
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
                    categorie = driver.findElement(By.xpath("//span[.='Categorie']"));
                    type = driver.findElement(By.xpath("//span[.='Type']"));
                } catch (Exception exception) {
                    System.out.println("PAGE without Filter: " + driver.getCurrentUrl());
                }
            }
            i++;
        }
    }

    @Test
    public void printHTTPlinks() throws InterruptedException {
        driver = getDriver();
        jsexecutor = (JavascriptExecutor) getDriver();
        driver.get(SITE_EL);
        List<WebElement> catalogLinks = driver.findElements(By.cssSelector(".elSubNavItemLinks-item-url"));
        int i = 0;
        for (WebElement e: catalogLinks) {
            jsexecutor.executeScript("document.querySelectorAll(\".elSubNavItemLinks-item-url\")[" + i + "].click();");
            Thread.sleep(500);
            System.out.println(jsexecutor.executeScript("return document.querySelectorAll(\".elSubNavItemLinks-item-url\")[" + i + "].href"));
            i++;
        }
    }
}
