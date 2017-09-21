package testSuite;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import webDriverFactory.WebDrivers;

public abstract class BaseTest {

    private static WebDriver driver;

    private final String CHROME = "CHROME";
    private final String FIREFOX = "FIREFOX";
    //private String browser = System.getProperty("browser");
    private String browser = CHROME;

    @BeforeClass
    public void setUpDriver() {
        System.out.println(browser + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        driver = WebDrivers.getDriver(browser);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
