package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpDialog {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By dialogMessageTitleSel = MobileBy.id("android:id/message");
    private static final By okBtnSel = MobileBy.xpath("//*[contains(@text, 'OK')]");

    public SignUpDialog(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String dialogMessageTitle() {
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(dialogMessageTitleSel));
        return appiumDriver.findElement(dialogMessageTitleSel).getText();
    }

    public SignUpDialog clickOK() {
        appiumDriver.findElement(okBtnSel).click();
        return this;
    }
}
