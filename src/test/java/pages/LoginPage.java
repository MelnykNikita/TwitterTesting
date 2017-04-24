package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.qatools.allure.annotations.Step;

public class LoginPage extends AbstractPage<LoginPage> {

    @FindBy(how = How.ID, using = "signin-email")
    private WebElement emailPlaceholder;
    @FindBy(how = How.ID, using = "signin-password")
    private WebElement passwordPlaceholder;
    @FindBy(how = How.XPATH, using = "//button[contains(@type,'submit')]")
    private WebElement buttonSubmit;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public LoginPage inputEmail(String email) {
        inputData(this.emailPlaceholder, email);
        return this;
    }

    @Step
    public LoginPage inputPassword(String password) {
        inputData(this.passwordPlaceholder, password);
        return this;
    }

    @Step
    public void submit() {
        this.buttonSubmit.click();
    }
}
