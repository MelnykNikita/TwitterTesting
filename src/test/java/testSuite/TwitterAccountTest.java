package testSuite;

import dataProvider.DataProviderSource;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.Formula1Page;
import pages.LoginPage;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.TestCaseId;
import testListener.TestListener;

@Listeners(TestListener.class)
@Features("Verify possibility to retweet posts")
@Stories("Chrome browser")
public class TwitterAccountTest extends BaseTest {

    private LoginPage loginPage;
    private AccountPage accountPage;
    private Formula1Page formula1Page;

    private final String LINK_TO_TWITTER = "http://twitter.com/";
    private final String LINK_TO_F1 = "http://twitter.com/f1";

    @TestCaseId("Test-1")
    @Test(dataProvider = "user1", dataProviderClass = DataProviderSource.class)
    public void verifyLoginIntoSystem(String email, String password) {
        getDriver().get(LINK_TO_TWITTER);
        loginPage = new LoginPage(getDriver());
        accountPage = new AccountPage(getDriver());
        loginPage
                .inputEmail(email)
                .inputPassword(password)
                .submit();
        Assert.assertTrue(accountPage.getHomeLink().isDisplayed(),
                "Account Page is not displayed...");
    }

    @TestCaseId("Test-2")
    @Test(dependsOnMethods = {"verifyLoginIntoSystem"})
    public void verifyRetweetOfPosts() {
        getDriver().get(LINK_TO_F1);
        formula1Page = new Formula1Page(getDriver());
        formula1Page.retweetPost();
        accountPage.clickHomeLink();
        int numberOfTweets = Integer.parseInt(accountPage.getNumberOfTweets());
        System.out.println("Number Of Tweets => " + numberOfTweets);
        Assert.assertTrue(numberOfTweets != 0,
                "There are no retweets on the page...");
    }


}
