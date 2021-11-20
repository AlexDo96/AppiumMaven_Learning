package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavigation;
import models.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;

public class LoginTest {
    public static void main(String[] args) {
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

            System.out.println("Sign up result: " + loginPage.signUpDialog().dialogMessageTitle());
            loginPage.signUpDialog().clickOK();

            // Fill Login form
            loginPage
                    .switchToLoginTab()
                    .clearLoginFields()
                    .inputUsername(userEmail)
                    .inputPassword(userPassword)
                    .clickLogin();

            System.out.println("Login description: " + loginPage.getLoginDescription());
            System.out.println("Login result: " + loginPage.loginDialog().dialogMessageTitle());
        } catch (Exception ignored) {
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }
}
