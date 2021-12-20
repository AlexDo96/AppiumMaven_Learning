package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test.BaseTest_Parallel;
import test_data.DataObjectBuilder;
import test_data.authentication.LoginCreds;

public class LoginTest_Parallel_Allure extends BaseTest_Parallel {
    private SoftAssert softAssert;

    @BeforeClass
    public void beforeClass() {
        softAssert = new SoftAssert();
    }

    @AfterClass
    public void afterClass() {
        softAssert.assertAll();
    }

    @TmsLink("TS_1234")
    @Description("Test login with data driven...")
    @Test(dataProvider = "loginCredsData", description = "Login Test")
    public void loginWithCorrectCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Login Page
        LoginPage loginPage = new LoginPage(appiumDriver);

        // Click Login feature
        BottomNavComponent bottomNavComponent = loginPage.bottomNavigation();
        bottomNavComponent.clickOnLoginLbl();

        // Fill Login form
        loginPage
                .clearLoginFields()
                .inputUsername(loginCreds.getUsername())
                .inputPassword(loginCreds.getPassword())
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

        // Close Login dialog
        loginPage.loginDialog().clickOK();
    }

    @DataProvider
    public LoginCreds[] loginCredsData() {
        String jsonLocationPath = "/src/main/resources/test-data/authentication/loginValidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }
}
