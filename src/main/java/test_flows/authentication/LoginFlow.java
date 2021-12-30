package test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.asserts.SoftAssert;
import test_data.authentication.LoginCreds;

public class LoginFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private LoginCreds loginCreds;
    private LoginPage loginPage;
    private SoftAssert softAssert;
    private String expectedLoginDescription = "When the device has Touch/FaceID (iOS) or FingerPrint enabled a biometrics button will be shown to use and test the login.";
    private String expectedLoginDialogMessage = "Success";
    private String customErrMsg = "[ERR] Login message title incorrect !!";
    private Boolean expectedMissingEmailDisplayed = true;
    private Boolean expectedMissingPasswordDisplayed = true;
    private String expectedMissingEmailText = "Please enter a valid email address";
    private String expectedMissingPasswordText = "Please enter at least 8 characters";

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, LoginCreds loginCreds) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
        this.loginCreds = loginCreds;
    }

    @Step("Login Page initialization")
    public LoginFlow initLoginPage() {
        loginPage = new LoginPage(appiumDriver);
        return this;
    }

    @Step("Access to Login form")
    public LoginFlow accessLoginForm() {
        if (loginPage == null) {
            initLoginPage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = loginPage.bottomNavigation();
        bottomNavComponent.clickOnLoginLbl();
        return this;
    }

    @Step("Input username as {loginCreds.username} and password as {loginCreds.password}")
    public LoginFlow login() {
        if (loginPage == null) {
            throw new RuntimeException("Please use method accessLoginForm first");
        }
        // Fill Login form
        loginPage
                .clearLoginFields()
                .inputUsername(loginCreds.getUsername())
                .inputPassword(loginCreds.getPassword())
                .clickLogin();
        return this;
    }

    public LoginFlow verifyLogin() {
        if (!loginCreds.getUsername().isEmpty() && !loginCreds.getPassword().isEmpty()) {
            String actualLoginDescription = loginPage.getLoginDescription();
            String actualLoginDialogMessage = loginPage.loginDialog().dialogMessageTitle();
            boolean isMessageCorrect = actualLoginDialogMessage.equals(expectedLoginDialogMessage);

            softAssert.assertTrue(isMessageCorrect, customErrMsg); // Using assertTrue
            softAssert.assertEquals(actualLoginDescription, expectedLoginDescription, "[ERR] Login description incorrect !!");
            softAssert.assertEquals(actualLoginDialogMessage, expectedLoginDialogMessage, "[ERR] Login message title incorrect !!");
        } else {
            /*
            Verify Login Fail
            Still in implement
            */
        }

        return this;
    }

    @Step("Check missing email and password")
    public LoginFlow checkMissingEmailAndPassword() {
        if (loginPage.checkUsernameField().isEmpty() && !loginPage.checkPasswordField().isEmpty()) {
            Boolean actualMissingEmailDisplayed = loginPage.getMissingEmailDescriptionDisplayed();
            softAssert.assertEquals(actualMissingEmailDisplayed, expectedMissingEmailDisplayed, "[ERR] Warning missing email is not displayed !!");
            String actualMissingEmailText = loginPage.getMissingEmailDescription();
            softAssert.assertEquals(actualMissingEmailText, expectedMissingEmailText, "[ERR] Warning missing email text incorrect !!");
        } else if (!loginPage.checkUsernameField().isEmpty() && loginPage.checkPasswordField().isEmpty()) {
            Boolean actualMissingPasswordDisplayed = loginPage.getMissingPasswordDescriptionDisplayed();
            softAssert.assertEquals(actualMissingPasswordDisplayed, expectedMissingPasswordDisplayed, "[ERR] Warning missing password is not displayed !!");
            String actualMissingPasswordText = loginPage.getMissingPasswordDescription();
            softAssert.assertEquals(actualMissingPasswordText, expectedMissingPasswordText, "[ERR] Warning missing password text incorrect !!");
        } else {
            Boolean actualMissingEmailDisplayed = loginPage.getMissingEmailDescriptionDisplayed();
            softAssert.assertEquals(actualMissingEmailDisplayed, expectedMissingEmailDisplayed, "[ERR] Warning missing email is not displayed !!");
            Boolean actualMissingPasswordDisplayed = loginPage.getMissingPasswordDescriptionDisplayed();
            softAssert.assertEquals(actualMissingPasswordDisplayed, expectedMissingPasswordDisplayed, "[ERR] Warning missing password is not displayed !!");
            String actualMissingEmailText = loginPage.getMissingEmailDescription();
            softAssert.assertEquals(actualMissingEmailText, expectedMissingEmailText, "[ERR] Warning missing email text incorrect !!");
            String actualMissingPasswordText = loginPage.getMissingPasswordDescription();
            softAssert.assertEquals(actualMissingPasswordText, expectedMissingPasswordText, "[ERR] Warning missing password text incorrect !!");
        }
        return this;
    }

    @Step("Click OK on Login dialog")
    public LoginFlow clickOK() {
        // Close Login dialog
        loginPage.loginDialog().clickOK();
        return this;
    }
}
