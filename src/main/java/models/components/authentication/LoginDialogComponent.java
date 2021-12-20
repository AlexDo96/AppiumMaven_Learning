package models.components.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginDialogComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By dialogMessageTitleSel = MobileBy.id("android:id/alertTitle");
    private static final By okBtnSel = MobileBy.xpath("//*[contains(@text, 'OK')]");

    public LoginDialogComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public String dialogMessageTitle() {
        // Explicit Wait
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 5L);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(dialogMessageTitleSel));
        return appiumDriver.findElement(dialogMessageTitleSel).getText();
    }

    @Step("Click OK on dialog")
    public LoginDialogComponent clickOK() {
        appiumDriver.findElement(okBtnSel).click();
        return this;
    }
}
