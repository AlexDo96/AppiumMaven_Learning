package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.authentication.LoginDialog;
import models.components.authentication.SignUpDialog;
import models.components.global.BottomNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By usernameSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-email']");
    private static final By passwordSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-password']");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By loginDescSel = MobileBy.xpath("//*[contains(@text, 'When the device')]");
    private static final By signUpTabSel = MobileBy.AccessibilityId("button-sign-up-container");
    private static final By loginTabSel = MobileBy.AccessibilityId("button-login-container");
    private static final By registerUsernameSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-email']");
    private static final By registerPasswordSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-password']");
    private static final By registerRepeatPasswordSel = MobileBy.xpath("//android.widget.EditText[@content-desc='input-repeat-password']");
    private static final By signUpBtnSel = MobileBy.AccessibilityId("button-SIGN UP");
    private BottomNavigation bottomNavigation; // Embedded Bottom Navigation into Login Page
    private LoginDialog loginDialog; // Embedded Login Dialog into Login Page
    private SignUpDialog signUpDialog; // Embedded Sign Up Dialog into Login Page

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginPage switchToSignUpTab() {
        appiumDriver.findElement(signUpTabSel).click();
        return this;
    }

    public LoginPage registerUsername(String username) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(registerUsernameSel)).sendKeys(username);
        return this;
    }

    public LoginPage registerPassword(String password) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(registerPasswordSel)).sendKeys(password);
        wait.until(ExpectedConditions.presenceOfElementLocated(registerRepeatPasswordSel)).sendKeys(password);
        return this;
    }

    public LoginPage clickSignUp() {
        appiumDriver.findElement(signUpBtnSel).click();
        return this;
    }

    public LoginPage switchToLoginTab() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(loginTabSel)).click();
        return this;
    }

    public LoginPage clearLoginFields() {
        appiumDriver.findElement(usernameSel).clear();
        appiumDriver.findElement(passwordSel).clear();
        return this;
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

    public SignUpDialog signUpDialog() {
        return new SignUpDialog(appiumDriver);
    }
}
