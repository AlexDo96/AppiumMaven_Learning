package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavigation {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginLblSel = MobileBy.AccessibilityId("Login");
    private static final By formsLblSel = MobileBy.AccessibilityId("Forms");
    private static final By swipeLblSel = MobileBy.AccessibilityId("Swipe");

    public BottomNavigation(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    // Return Mobile Element
    public MobileElement loginLbl() {
        return appiumDriver.findElement(loginLblSel);
    }

    public MobileElement formsLbl() {
        return appiumDriver.findElement(formsLblSel);
    }

    public MobileElement swipeLbl() {
        return appiumDriver.findElement(swipeLblSel);
    }

    // Main interaction method
    public void clickOnLoginLbl() {
        appiumDriver.findElement(loginLblSel).click();
    }
}
