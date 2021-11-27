package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavigation;
import models.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpTest {
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
    public void SignUp() {
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

            // Switch to Sign Up tab and register an account
            loginPage
                    .switchToSignUpTab()
                    .registerUsername(userEmail)
                    .registerPassword(userPassword)
                    .clickSignUp();

            // Verification
            String expectedSignUpDialogMessage = "You successfully signed up!";
            String actualSignUpDialogMessage = loginPage.signUpDialog().dialogMessageTitle();
            softAssert.assertEquals(actualSignUpDialogMessage, expectedSignUpDialogMessage, "[ERR] Sign up message title incorrect !!");

            loginPage.signUpDialog().clickOK();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
