package test.authentication;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import models.components.global.BottomNavComponent;
import models.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import test_data.DataObjectBuilder;
import test_data.authentication.LoginCreds;

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

    @Test(dataProvider = "loginCredsData")
    public void loginWithCorrectCreds(LoginCreds loginCreds) {
        DriverFactory.startAppiumServer();

        try {
            AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
            // Login Page
            LoginPage loginPage = new LoginPage(androidDriver);

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.stopAppiumServer();
        }
    }

    @DataProvider
    public LoginCreds[] loginCredsData() {
        String jsonLocationPath = "/src/main/resources/test-data/authentication/loginValidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }
}
