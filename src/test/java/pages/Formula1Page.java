package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.List;

public class Formula1Page extends AbstractPage<AccountPage> {

    @FindBy(how = How.XPATH, using = "//li[contains(@id, 'stream-item-tweet')]/div")
    private List<WebElement> listWithTweetItems;

    //div[2]/div[6]/div[2]/div[2]/button[1]

    @FindBy(how = How.XPATH, using = "//li[contains(@id, 'stream-item-tweet')]//button[1]")
    private WebElement buttonRetweet;
    @FindBy(how = How.ID, using = "retweet-tweet-dialog-dialog")
    private WebElement retweetDialog;
    @FindBy(how = How.XPATH, using = ".//form/div[2]/div[3]/button")
    private WebElement buttonRetweetOnDialog;

    private By buttonRetweetLocator = new By.ByXPath(".//div[2]/div[2]/button[1]/div[1]");

    public Formula1Page(WebDriver driver) {
        super(driver);
    }

    public RetweetPage clickOnButtonRetweet() {
        clickElement(buttonRetweet);
        return new RetweetPage(driver);
    }

    @Step
    public void retweetPost() {

        for (int i = 0; i < listWithTweetItems.size(); i++) {
            if (listWithTweetItems.get(i) != null)
            {
                scrollToElement(buttonRetweet, false);
                System.out.println("Scroll to Element");

                LocalDate date = LocalDate.from(LocalDateTime.of(2017, Month.APRIL,25,21, 0));

                Timestamp timestamp = getTweetTimestamp(listWithTweetItems.get(i));
                LocalDate dateOfTweet = timestamp.toLocalDateTime().toLocalDate();
                System.out.println("date of tweet => " + timestamp);

                Period period = Period.between(date, dateOfTweet);
                System.out.println("period => " + period.getDays());

                // find tweets and making retweets for the last days(period)
                if (period.getDays() >= 1) {
                    //clickElement(listWithTweetItems.get(i).findElement(buttonRetweetLocator));

                    clickElement(listWithTweetItems.get(i).findElement(buttonRetweetLocator));
                    //listWithTweetItems.get(i).findElement(buttonRetweetLocator).click();
                    System.out.println("click ON button Retweet is done...");

                    //*[@id="retweet-tweet-dialog-dialog"]/div[2]/form/div[2]/div[3]/button/span[1]/span
                    retweetDialog.findElement
                            (new By.ByXPath(".//div[2]/form/div[2]/div[3]")).click();
                    //*[@id="retweet-tweet-dialog-dialog"]/div[2]/form/div[2]/div[3]
                    System.out.println("click ON buttonRetweetOnDialog is done...");

                } else {
                    System.out.println("Ending...");
                    break;
                }
            }
        }
    }

    public Timestamp getTweetTimestamp(WebElement tweet)
    {
        WebElement timestampElement = tweet.findElement(By.xpath(".//span[contains(@class,'_timestamp')]"));
        return new Timestamp(Long.parseLong(timestampElement.getAttribute("data-time-ms")));
    }

}
