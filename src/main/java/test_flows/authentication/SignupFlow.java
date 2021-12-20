package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.asserts.SoftAssert;
import test_data.authentication.LoginCreds;

public class SignupFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private LoginPage loginPage;
    private SoftAssert softAssert;
    private String expectedSignUpDialogMessage = "You successfully signed up!";
    private String customErrMsg = "[ERR] Login message title incorrect !!";

    public SignupFlow(AppiumDriver<MobileElement> appiumDriver) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
    }

    @Step("Login Page initialization")
    public SignupFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    @Step("Access to Login form")
    public SignupFlow accessLoginForm() {
        if (loginPage == null) {
            initLoginPage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = loginPage.bottomNavigation();
        bottomNavComponent.clickOnLoginLbl();
        return this;
    }

    @Step("Switch to Signup Tab")
    public SignupFlow switchToSignUpTab() {
        loginPage.switchToSignUpTab();
        return this;
    }

    @Step("Input username as {loginCreds.username} and password as {loginCreds.password} and retype password as {loginCreds.password}")
    public SignupFlow signup(LoginCreds loginCreds) {
        if (loginPage == null) {
            throw new RuntimeException("Please use method accessLoginForm first");
        }
        // Fill Signup form
        loginPage
                .signUpInputUsername(loginCreds.getUsername())
                .signUpInputPassword(loginCreds.getPassword())
                .signUpRetypePassword(loginCreds.getPassword())
                .clickSignUp();
        return this;
    }

    public SignupFlow verifySignUpSuccess() {
        String actualSignUpDialogMessage = loginPage.signUpDialog().dialogMessageTitle();
        softAssert.assertEquals(actualSignUpDialogMessage, expectedSignUpDialogMessage, "[ERR] Sign up message title incorrect !!");
        return this;
    }

    public SignupFlow verifySignUpFail() {
        return this;
    }

    @Step("Click OK on Signup dialog")
    public SignupFlow clickOK() {
        // Close Signup dialog
        loginPage.signUpDialog().clickOK();
        return this;
    }
}
