package test.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import test.BaseTest_Parallel;
import test_flows.swipe.SwipeFlow;

public class Swipe_TestParallelFlow extends BaseTest_Parallel {
    @Description("Test SWIPE Page")
    @Test(description = "Swipe_001: User can swipe and texts are displayed correctly")
    public void swipeHorizontalUntil() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        SwipeFlow swipeFlow = new SwipeFlow(appiumDriver);
        swipeFlow
                .accessSwipePage()
                .swipeHorizontalAndCheckTextsDisplayed();
    }

    @Description("Test SWIPE Page")
    @Test(description = "Swipe_002: Swipe vertically to see the icon at the end")
    public void swipeVerticalUntil() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        SwipeFlow swipeFlow = new SwipeFlow(appiumDriver);
        swipeFlow
                .accessSwipePage()
                .swipeVerticalAndCheckIconDisplayed();
    }
}
