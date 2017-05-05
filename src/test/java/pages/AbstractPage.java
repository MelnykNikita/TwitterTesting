package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage<T extends AbstractPage<T>> {

    protected static WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor javascriptExecutor;
    protected final static int MAX_TIME_WAIT = 10;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, MAX_TIME_WAIT);
        this.javascriptExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public T waitForElementToBeClickable(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public T waitForVisibilityOfElement(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) this;
    }

    public void clickElement(WebElement webElement) {
        waitForElementToBeClickable(webElement);
        webElement.click();
    }

    public void typeDataInField(WebElement webElement, String data) {
        waitForVisibilityOfElement(webElement);
        webElement.clear();
        webElement.sendKeys(data);
    }

    public boolean isElementPresent(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void scrollToElement(WebElement element) {
        javascriptExecutor.executeScript
                ("window.scrollTo(0" + "," + element.getLocation().x + ")");
    }

    public T waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);
        return (T) this;
    }

    protected WebElement scrollToElement(WebElement element, boolean isIntoView) {
        javascriptExecutor
                .executeScript(String.format("arguments[0].scrollIntoView(%b);", isIntoView), element);
        return element;
    }
}
