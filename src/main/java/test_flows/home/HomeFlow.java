package test_flows.home;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.HomePage;
import org.testng.asserts.SoftAssert;

public class HomeFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private HomePage homePage;
    private SoftAssert softAssert;
    private String expectedAppPurposeText = "Demo app for the appium-boilerplate";
    private String expectedSupportText = "Support";
    private Boolean expectedAppPurposeDisplayed = true;
    private Boolean expectedAndroidDisplayed = true;
    private Boolean expectedIOSDisplayed = true;
    private Boolean expectedSupportDisplayed = true;

    public HomeFlow(AppiumDriver<MobileElement> appiumDriver) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
    }

    @Step("Home Page initialization")
    public HomeFlow initHomePage() {
        homePage = new HomePage(appiumDriver);
        return this;
    }

    @Step("Access to Home page")
    public HomeFlow accessHomePage() {
        if (homePage == null) {
            initHomePage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = homePage.bottomNavigation();
        bottomNavComponent.clickOnHomeLbl();
        return this;
    }

    @Step("Make sure App purpose displayed")
    public HomeFlow checkAppPurposeDisplayed() {
        if (homePage == null) {
            throw new RuntimeException("Please use method accessHomePage first");
        }

        Boolean actualAppPurposeDisplayed = homePage.getAppPurposeDisplayed();
        softAssert.assertEquals(actualAppPurposeDisplayed, expectedAppPurposeDisplayed, "[ERR] App Purpose is not displayed !!");
        String actualAppPurposeText = homePage.getAppPurposeDescription();
        softAssert.assertEquals(actualAppPurposeText, expectedAppPurposeText, "[ERR] App Purpose text incorrect !!");
        return this;
    }

    @Step("Make sure Support channel displayed")
    public HomeFlow checkSupportChannelDisplayed() {
        if (homePage == null) {
            throw new RuntimeException("Please use method accessHomePage first");
        }

        Boolean actualAndroidDisplayed = homePage.getAndroidChannelDisplayed();
        softAssert.assertEquals(actualAndroidDisplayed, expectedAndroidDisplayed, "[ERR] Android channel is not displayed !!");
        Boolean actualIOSDisplayed = homePage.getIOSChannelDisplayed();
        softAssert.assertEquals(actualIOSDisplayed, expectedIOSDisplayed, "[ERR] IOS channel is not displayed !!");
        return this;
    }
}
