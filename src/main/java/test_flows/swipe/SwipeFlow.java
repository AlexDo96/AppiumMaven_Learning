package test_flows.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.SwipePage;
import org.testng.asserts.SoftAssert;

public class SwipeFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private SwipePage swipePage;
    private SoftAssert softAssert;

    public SwipeFlow(AppiumDriver<MobileElement> appiumDriver) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
    }

    @Step("Swipe Page initialization")
    public SwipeFlow initSwipePage() {
        swipePage = new SwipePage(appiumDriver);
        return this;
    }

    @Step("Access to Swipe page")
    public SwipeFlow accessSwipePage() {
        if (swipePage == null) {
            initSwipePage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = swipePage.bottomNavigation();
        bottomNavComponent.clickOnSwipeLbl();
        return this;
    }

    @Step("Swipe horizontally and texts are displayed correctly")
    public SwipeFlow swipeHorizontalAndCheckTextsDisplayed() {
        if (swipePage == null) {
            throw new RuntimeException("Please use method accessSwipePage first");
        }
        swipePage.swipeHorizontally();
        return this;
    }

    @Step("Swipe vertically to see icon at the end")
    public SwipeFlow swipeVerticalAndCheckIconDisplayed() {
        if (swipePage == null) {
            throw new RuntimeException("Please use method accessSwipePage first");
        }
        swipePage.swipeVertically();
        return this;
    }
}
