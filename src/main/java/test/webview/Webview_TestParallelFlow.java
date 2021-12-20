package test.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import test.BaseTest_Parallel;
import test_flows.webview.WebviewFlow;

public class Webview_TestParallelFlow extends BaseTest_Parallel {
    @Description("Test Webview Page")
    @Test(description = "Make sure the menu text and hyperlink displayed correctly")
    public void checkMenuTextAndHyperlinkDisplayed() throws InterruptedException {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        WebviewFlow webviewFlow = new WebviewFlow(appiumDriver);
        webviewFlow
                .accessWebviewPage()
                .clickNavigationToggle()
                .listAllMenu();
    }
}
