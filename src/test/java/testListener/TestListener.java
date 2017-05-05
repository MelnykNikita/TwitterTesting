package testListener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import pages.AbstractPage;
import ru.yandex.qatools.allure.annotations.Attachment;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        attachScreenshot();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] attachScreenshot() {
        byte[] screenshotAs = null;
        try {
            screenshotAs = ((TakesScreenshot) AbstractPage.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return screenshotAs;
    }
}
