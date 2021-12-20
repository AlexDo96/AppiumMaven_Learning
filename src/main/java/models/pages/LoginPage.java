package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.authentication.CredsFormComponent;
import models.components.authentication.LoginDialogComponent;
import models.components.authentication.SignUpDialogComponent;
import models.components.authentication.SignUpFormComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends GeneralPage{
    private final AppiumDriver<MobileElement> appiumDriver;
    private CredsFormComponent credsFormComponent;
    private SignUpFormComponent signUpFormComponent;

    private static final By loginDescSel = MobileBy.xpath("//*[contains(@text, 'When the device')]");
    private static final By signUpTabSel = MobileBy.AccessibilityId("button-sign-up-container");
    private static final By loginTabSel = MobileBy.AccessibilityId("button-login-container");
    private static final By missingEmailDescSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Login-screen\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]");
    private static final By missingPasswordDescSel = MobileBy.xpath("//android.widget.ScrollView[@content-desc=\"Login-screen\"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[*]/android.widget.TextView[2]");

    public LoginPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        credsFormComponent = new CredsFormComponent(appiumDriver);
    }

    @Step("Type username as {username}")
    public LoginPage inputUsername(String username) {
        credsFormComponent.inputUsername(username);
        return this;
    }

    @Step("Type password as {password}")
    public LoginPage inputPassword(String password) {
        credsFormComponent.inputPassword(password);
        return this;
    }

    @Step("Click login button")
    public LoginPage clickLogin() {
        credsFormComponent.clickLoginBtn();
        return this;
    }

    public LoginPage switchToSignUpTab() {
        appiumDriver.findElement(signUpTabSel).click();
        signUpFormComponent = new SignUpFormComponent(appiumDriver);
        return this;
    }

    public LoginPage signUpInputUsername(String username) {
        signUpFormComponent.inputUsername(username);
        return this;
    }

    public LoginPage signUpInputPassword(String password) {
        signUpFormComponent.inputPassword(password);
        return this;
    }

    public LoginPage signUpRetypePassword(String password) {
        signUpFormComponent.retypePassword(password);
        return this;
    }

    public LoginPage clickSignUp() {
        signUpFormComponent.clickSignUpBtn();
        return this;
    }

    public LoginPage switchToLoginTab() {
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(loginTabSel)).click();
        return this;
    }

    @Step("Clear out login fields")
    public LoginPage clearLoginFields() {
        credsFormComponent.clearUsernameField();
        credsFormComponent.clearPasswordField();
        return this;
    }

    public String getLoginDescription() {
        return appiumDriver.findElement(loginDescSel).getText();
    }

    public String getMissingEmailDescription() {
        return appiumDriver.findElement(missingEmailDescSel).getText();
    }

    public String getMissingPasswordDescription() {
        return appiumDriver.findElement(missingPasswordDescSel).getText();
    }

    public boolean getMissingEmailDescriptionDisplayed() {
        return appiumDriver.findElement(missingEmailDescSel).isDisplayed();
    }

    public boolean getMissingPasswordDescriptionDisplayed() {
        return appiumDriver.findElement(missingPasswordDescSel).isDisplayed();
    }

    public BottomNavComponent bottomNavigation() {
        return new BottomNavComponent(appiumDriver);
    }

    public LoginDialogComponent loginDialog() {
        return new LoginDialogComponent(appiumDriver);
    }

    public SignUpDialogComponent signUpDialog() {
        return new SignUpDialogComponent(appiumDriver);
    }
}
