package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Formula1Page extends AbstractPage<Formula1Page> {

    @FindBy(how = How.CLASS_NAME, using = "stream-items js-navigable-stream")
    private List<WebElement> timestampOnPost;

    public Formula1Page(WebDriver driver) {
        super(driver);
    }

    public WebElement getTimestampOnPost() {
        return timestampOnPost.get(0).findElement(new By.ByXPath("")).findElement();
    }
}
