package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RetweetPage extends AbstractPage<RetweetPage> {

    @FindBy(how = How.XPATH, using = "//*[@id=\"retweet-tweet-dialog-dialog\"]/div[2]/form/div[2]/div[3]")
    private WebElement retweetButtonOnDialog;

    public RetweetPage(WebDriver driver) {
        super(driver);
    }

    public Formula1Page clickRetweetButtonOnDialog() {
        clickElement(retweetButtonOnDialog);
        return new Formula1Page(driver);
    }
}
