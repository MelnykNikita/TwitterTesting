package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPage extends AbstractPage<AccountPage> {

    @FindBy(how = How.XPATH, using = "//*[@id=\"global-nav-home\"]/a/span[2]")
    private WebElement homeLink;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHomeLink() {
        return homeLink;
    }

}
