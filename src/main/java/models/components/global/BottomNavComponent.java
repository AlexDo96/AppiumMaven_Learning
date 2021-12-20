package models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BottomNavComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By homeLblSel = MobileBy.AccessibilityId("Home");
    private static final By webviewLblSel = MobileBy.AccessibilityId("Webview");
    private static final By loginLblSel = MobileBy.AccessibilityId("Login");
    private static final By formsLblSel = MobileBy.AccessibilityId("Forms");
    private static final By swipeLblSel = MobileBy.AccessibilityId("Swipe");
    private static final By dragLblSel = MobileBy.AccessibilityId("Drag");

    public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    // Return Mobile Element
    public MobileElement homeLbl() {
        return appiumDriver.findElement(homeLblSel);
    }

    public MobileElement webviewLbl() {
        return appiumDriver.findElement(webviewLblSel);
    }

    public MobileElement loginLbl() {
        return appiumDriver.findElement(loginLblSel);
    }

    public MobileElement formsLbl() {
        return appiumDriver.findElement(formsLblSel);
    }

    public MobileElement swipeLbl() {
        return appiumDriver.findElement(swipeLblSel);
    }

    public MobileElement dragLbl() {
        return appiumDriver.findElement(dragLblSel);
    }

    // Main interaction method
    @Step("Click on Home label")
    public void clickOnHomeLbl() {
        appiumDriver.findElement(homeLblSel).click();
    }

    @Step("Click on Webview label")
    public void clickOnWebviewLbl() {
        appiumDriver.findElement(webviewLblSel).click();
    }

    @Step("Click on Login label")
    public void clickOnLoginLbl() {
        appiumDriver.findElement(loginLblSel).click();
    }

    @Step("Click on Form label")
    public void clickOnFormLbl() {
        appiumDriver.findElement(formsLblSel).click();
    }

    @Step("Click on Swipe label")
    public void clickOnSwipeLbl() {
        appiumDriver.findElement(swipeLblSel).click();
    }

    @Step("Click on Drag label")
    public void clickOnDragLbl() {
        appiumDriver.findElement(swipeLblSel).click();
    }
}
