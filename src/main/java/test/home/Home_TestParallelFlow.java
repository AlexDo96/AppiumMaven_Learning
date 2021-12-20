package test.home;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import test.BaseTest_Parallel;
import test_flows.home.HomeFlow;

public class Home_TestParallelFlow extends BaseTest_Parallel {
    @Description("Test HOME Page")
    @Test(description = "HomePage_001: Make sure \"App purpose\" displayed")
    public void checkAppPurposeDisplayed() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        HomeFlow homeFlow = new HomeFlow(appiumDriver);
        homeFlow
                .accessHomePage()
                .checkAppPurposeDisplayed();
    }

    @Description("Test HOME Page")
    @Test(description = "HomePage_002: Make sure \"Support\" channel displayed")
    public void checkSupportChannelDisplayed() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        HomeFlow homeFlow = new HomeFlow(appiumDriver);
        homeFlow
                .accessHomePage()
                .checkSupportChannelDisplayed();
    }
}
