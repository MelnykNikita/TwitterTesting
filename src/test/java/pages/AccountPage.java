package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class AccountPage extends AbstractPage<AccountPage> {

    @FindBy(how = How.XPATH, using = "//*[@id='global-nav-home']")
    private WebElement homeLink;
    @FindBy(how = How.XPATH, using ="//*[@id='page-container']/div[1]/div[1]/div/div[3]/ul/li/a/span[2]")
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
