package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDialog {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By dialogMessageTitleSel = MobileBy.id("android:id/alertTitle");

    public LoginDialog(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String dialogMessageTitle() {
        // Explicit Wait
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 15);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(dialogMessageTitleSel));
        return appiumDriver.findElement(dialogMessageTitleSel).getText();
    }
}