package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavigation;
import models.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class LoginTest {
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
        DriverFactory.startAppiumServer();

        int length = 8;
        boolean useLetters = true;
        boolean useNumbersForEmail = false;
        boolean useNumbersForPassword = true;
        String generatedStringEmail = RandomStringUtils.random(length, useLetters, useNumbersForEmail);
        String generatedStringPassword = RandomStringUtils.random(length, useLetters, useNumbersForPassword);
        String userEmail = generatedStringEmail + "@gmail.com";
        String userPassword = generatedStringPassword;

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            // Login Page
            LoginPage loginPage = new LoginPage(androidDriver);

            // Click Login feature
            BottomNavigation bottomNavigation = loginPage.bottomNavigation();
            bottomNavigation.clickOnLoginLbl();

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
