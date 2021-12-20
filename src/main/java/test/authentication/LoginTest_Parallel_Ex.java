package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.BaseTest_Parallel;

public class LoginTest_Parallel_Ex extends BaseTest_Parallel {
    private SoftAssert softAssert;

    @BeforeClass
    public void beforeClass() {
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void afterClass() {
        softAssert.assertAll();
    }

    @Test
    public void loginWithCorrectCreds() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Init authentication
        int length = 8;
        boolean useLetters = true;
        boolean useNumbersForEmail = false;
        boolean useNumbersForPassword = true;
        String generatedStringEmail = RandomStringUtils.random(length, useLetters, useNumbersForEmail);
        String generatedStringPassword = RandomStringUtils.random(length, useLetters, useNumbersForPassword);
        String userEmail = generatedStringEmail + "@gmail.com";
        String userPassword = generatedStringPassword;

        // Login Page
        LoginPage loginPage = new LoginPage(appiumDriver);

        // Click Login feature
        BottomNavComponent bottomNavComponent = loginPage.bottomNavigation();
        bottomNavComponent.clickOnLoginLbl();

        // Fill Login form
        loginPage
                .clearLoginFields()
                .inputUsername(userEmail)
                .inputPassword(userPassword)
                .clickLogin();

        // Verification
        String expectedLoginDescription = "When the device has Touch/FaceID (iOS) or FingerPrint enabled a biometrics button will be shown to use and test the login.";
        String expectedLoginDialogMessage = "Success";
        String actualLoginDescription = loginPage.getLoginDescription();
        String actualLoginDialogMessage = loginPage.loginDialog().dialogMessageTitle();
        boolean isMessageCorrect = actualLoginDialogMessage.equals(expectedLoginDialogMessage);
        String customErrMsg = "[ERR] Login message title incorrect !!";

        softAssert.assertTrue(isMessageCorrect, customErrMsg); // Using assertTrue
        softAssert.assertEquals(actualLoginDescription, expectedLoginDescription, "[ERR] Login description incorrect !!");
        softAssert.assertEquals(actualLoginDialogMessage, expectedLoginDialogMessage, "[ERR] Login message title incorrect !!");
    }
}
