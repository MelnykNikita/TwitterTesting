package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class Formula1Page extends AbstractPage<Formula1Page> {

    @FindBy(how = How.XPATH, using = "//li[contains(@id, 'stream-item-tweet')]")
    private List<WebElement> listWithTweetItems;
    @FindBy(how = How.XPATH, using = "//li[contains(@id, 'stream-item-tweet')]//button[1]")
    private WebElement buttonRetweet;
    @FindBy(how = How.XPATH, using = "//*[@id=\"retweet-tweet-dialog-dialog\"]/div[2]/form/div[2]/div[3]")
    private WebElement buttonRetweetOnDialog;

    private By buttonRetweetLocator = new By.ByXPath(".//div[2]/div[2]/button[1]");

    public List<WebElement> allItems;

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
                scrollToElement(buttonRetweet);
                System.out.println("Scroll to Element");

                if (listWithTweetItems.get(i)
                        .findElement(new By.ByXPath
                                (".//span[contains(@class,'_timestamp')]"))
                        .isEnabled())
                {
                    clickElement(listWithTweetItems.get(i).findElement(buttonRetweetLocator));
                    System.out.println("click ON button Retweet is done...");

                    clickElement(buttonRetweetOnDialog);
                    System.out.println("click ON buttonRetweetOnDialog is done...");

                }
            }
        }
    }

    public WebElement getButtonRetweet() {
        return buttonRetweet;
    }

    public void addElementsToList(WebElement element) {
        listWithTweetItems.add(element);
    }

    public boolean isListEmpty() {
        return listWithTweetItems == null;
    }

    public void printList() {
        for (WebElement element: listWithTweetItems) {
            System.out.println(element.toString());
        }
    }

    public int getSizeOfList() {
        return listWithTweetItems.size();
    }
}
