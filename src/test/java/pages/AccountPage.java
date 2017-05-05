package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

public class AccountPage extends AbstractPage<AccountPage> {

    @FindBy(xpath = "//*[@id='global-nav-home']")
    private WebElement homeLink;
    @FindBy(css ="span[class='ProfileCardStats-statValue']")
    private WebElement numberOfTweets;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHomeLink() {
        return homeLink;
    }

    @Step
    public AccountPage clickHomeLink() {
        scrollToElement(homeLink);
        homeLink.click();
        return this;
    }

    public String getNumberOfTweets() {
        return numberOfTweets.getText();
    }

}
