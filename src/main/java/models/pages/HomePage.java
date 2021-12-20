package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;

public class HomePage extends GeneralPage{
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By appPurposeSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Home-screen\"]/*/android.widget.TextView[2]");
    private static final By androidChannelSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Home-screen\"]/*/android.widget.TextView[3]");
    private static final By iOSChannelSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Home-screen\"]/*/android.widget.TextView[4]");
    private static final By supportChannelSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Home-screen\"]/*/android.widget.TextView[5]");

    public HomePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    public String getAppPurposeDescription() {
        return appiumDriver.findElement(appPurposeSel).getText();
    }

    public String getSupportDescription() {
        return appiumDriver.findElement(supportChannelSel).getText();
    }

    public boolean getAppPurposeDisplayed() {
        return appiumDriver.findElement(appPurposeSel).isDisplayed();
    }

    public boolean getAndroidChannelDisplayed() {
        return appiumDriver.findElement(androidChannelSel).isDisplayed();
    }

    public boolean getIOSChannelDisplayed() {
        return appiumDriver.findElement(iOSChannelSel).isDisplayed();
    }

    public boolean getSupportDisplayed() {
        return appiumDriver.findElement(supportChannelSel).isDisplayed();
    }

    public BottomNavComponent bottomNavigation() {
        return new BottomNavComponent(appiumDriver);
    }
}
