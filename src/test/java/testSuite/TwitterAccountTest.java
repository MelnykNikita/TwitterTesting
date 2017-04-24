package testSuite;

import dataProvider.DataProviderSource;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testListener.TestListener;

@Listeners(TestListener.class)
@Features("Verify possibility to retweet posts")
@Stories("Chrome browser")
public class TwitterAccountTest extends BaseTest {

    private LoginPage loginPage;
    private AccountPage accountPage;

    private final String CHROME = "CHROME";
    private final String LINK = "http://twitter.com/";

    @BeforeClass
    public void setUpDriver() {
        super.setUpDriver(CHROME);
    }

    @AfterClass
    public void quitDriver() {
        super.quitDriver();
    }

    @TestCaseId("Test-1")
    @Test(dataProvider = "user1", dataProviderClass = DataProviderSource.class)
    public void verifyLoginIntoSystem(String email, String password) {
        setPreconditions();
        loginPage
                .inputEmail(email)
                .inputPassword(password)
                .submit();
        Assert.assertTrue(accountPage.isElementDisplayed(accountPage.getHomeLink()));
    }

    @Step
    private void setPreconditions() {
        getDriver().get(LINK);
        loginPage = new LoginPage(getDriver());
        accountPage = new AccountPage(getDriver());
    }
}
