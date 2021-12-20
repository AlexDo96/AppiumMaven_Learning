package models.components.forms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActiveButtonDialogComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By activeBtnDialogAlertSel = MobileBy.id("android:id/alertTitle");
    private static final By activeBtnDialogMessageSel = MobileBy.id("android:id/message");
    private static final By activeBtnDialogOKSel = MobileBy.id("android:id/button1");

    public ActiveButtonDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public boolean getDialogAlertDisplayed() {
        return appiumDriver.findElement(activeBtnDialogAlertSel).isDisplayed();
    }

    public boolean getDialogMessageDisplayed() {
        return appiumDriver.findElement(activeBtnDialogMessageSel).isDisplayed();
    }

    public String dialogAlertTitle() {
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(activeBtnDialogAlertSel));
        return appiumDriver.findElement(activeBtnDialogAlertSel).getText();
    }

    public String dialogMessageTitle() {
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(activeBtnDialogMessageSel));
        return appiumDriver.findElement(activeBtnDialogMessageSel).getText();
    }

    public ActiveButtonDialogComponent clickOK() {
        appiumDriver.findElement(activeBtnDialogOKSel).click();
        return this;
    }
}
