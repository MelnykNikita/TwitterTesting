package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class AccountPage extends AbstractPage<AccountPage> {

    @FindBy(how = How.XPATH, using = "//*[@id='global-nav-home']")
    private WebElement homeLink;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHomeLink() {
        return homeLink;
    }

    @Step
    public AccountPage clickHomeLink() {
        //clickElement(homeLink);
        scrollToElement(homeLink);
        homeLink.click();
        /*getDriver().findElement
                (new By.ByXPath("/*//*[@id='global-nav-home']"))
                .findElement(new By.ByXPath(".//a/span[1]")).click();*/
        //homeLink.click();
        return this;
    }

}
