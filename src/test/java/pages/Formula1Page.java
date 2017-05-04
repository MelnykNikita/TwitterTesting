package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.List;

public class Formula1Page extends AbstractPage<AccountPage> {

    @FindBy(xpath = "//li[contains(@id, 'stream-item-tweet')]/div")
    private List<WebElement> listWithTweetItems;
    @FindBy(css = "button[data-modal='ProfileTweet-retweet']")
    private WebElement buttonRetweet;
    @FindBy(css = "button[class='btn primary-btn retweet-action']")
    private WebElement buttonRetweetOnDialog;

    private By buttonRetweetLocator = new By.ByCssSelector("button[data-modal='ProfileTweet-retweet']");

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

                LocalDate date = LocalDate.from(LocalDateTime.of(2017, Month.MAY,03,22, 0));

                Timestamp timestamp = getTweetTimestamp(listWithTweetItems.get(i));
                LocalDate dateOfTweet = timestamp.toLocalDateTime().toLocalDate();
                System.out.println("date of tweet => " + timestamp);

                Period period = Period.between(date, dateOfTweet);
                System.out.println("period => " + period.getDays());

                // find tweets and making retweets for the last days(period)
                if (period.getDays() >= 1) {

                    clickElement(listWithTweetItems.get(i).findElement(buttonRetweetLocator));

                    System.out.println("click ON button Retweet is done...");

                    clickElement(buttonRetweetOnDialog);
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
