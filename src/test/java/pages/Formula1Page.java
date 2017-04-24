package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Formula1Page extends AbstractPage<Formula1Page> {

    @FindBy(how = How.CLASS_NAME, using = "_timestamp js-short-timestamp js-relative-timestamp")
    private WebElement timestampOnPost;

    public Formula1Page(WebDriver driver) {
        super(driver);
    }

    public WebElement getTimestampOnPost() {
        return timestampOnPost;
    }
}
