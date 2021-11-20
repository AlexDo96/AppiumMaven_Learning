package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.authentication.LoginDialog;
import models.components.global.BottomNavigation;
import org.openqa.selenium.By;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-email']");
    private static final By passwordSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-password']");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By loginDescSel = MobileBy.xpath("//*[contains(@text, 'When the device')]");
    private BottomNavigation bottomNavigation; // Embedded Bottom Navigation into Login Page
    private LoginDialog loginDialog; // Embedded Login Diaglog into Login Page

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginPage inputUsername(String username) {
        appiumDriver.findElement(usernameSel).sendKeys(username);
        return this;
    }

    public LoginPage inputPassword(String password) {
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    public String getLoginDescription() {
        return appiumDriver.findElement(loginDescSel).getText();
    }

    public BottomNavigation bottomNavigation() {
        return new BottomNavigation(appiumDriver);
    }

    public LoginDialog loginDialog() {
        return new LoginDialog(appiumDriver);
    }
}
