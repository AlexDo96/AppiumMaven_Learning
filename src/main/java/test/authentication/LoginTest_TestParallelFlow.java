package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest_Parallel;
import test_data.DataObjectBuilder;
import test_data.authentication.LoginCreds;
import test_flows.authentication.LoginFlow;

public class LoginTest_TestParallelFlow extends BaseTest_Parallel {
    @TmsLink("TS_5678")
    @Description("Login_001: Make sure \"missing email and password\" displayed")
    @Test(dataProvider = "loginMissingEmailAndPassword", description = "Login test when missing email and password", priority = 3)
    public void loginWhenMissingEmailAndPassword(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCreds);
        loginFlow
                .accessLoginForm()
                .login()
                .checkMissingEmailAndPassword();
    }

    @TmsLink("TS_5678")
    @Description("Login_002: Make sure \"missing email\" displayed")
    @Test(dataProvider = "loginMissingEmail", description = "Login test when missing email", priority = 4)
    public void loginWhenMissingEmail(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCreds);
        loginFlow
                .accessLoginForm()
                .login()
                .checkMissingEmailAndPassword();
    }

    @TmsLink("TS_5678")
    @Description("Login_003: Make sure \"missing password\" displayed")
    @Test(dataProvider = "loginMissingPassword", description = "Login test when missing password", priority = 5)
    public void loginWhenMissingPassword(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCreds);
        loginFlow
                .accessLoginForm()
                .login()
                .checkMissingEmailAndPassword();
    }

    @TmsLink("TS_1234")
    @Description("Login_004: User can login with correct creds")
    @Test(dataProvider = "loginValidCredsData", description = "Login test with valid credential", priority = 1)
    public void loginWithValidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCreds);
        loginFlow
                .accessLoginForm()
                .login()
                .verifyLogin()
                .clickOK();
    }

    @TmsLink("TS_5678")
    @Description("Login_005: User cannot login with incorrect creds")
    @Test(dataProvider = "loginInvalidCredsData", description = "Login test with invalid credential", priority = 2)
    public void loginWithInvalidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCreds);
        loginFlow
                .accessLoginForm()
                .login()
                .verifyLogin();
    }

    @DataProvider
    public LoginCreds[] loginValidCredsData() {
        String jsonLocationPath = "/test-data/authentication/loginValidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] loginInvalidCredsData() {
        String jsonLocationPath = "/test-data/authentication/loginInvalidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] loginMissingEmailAndPassword() {
        String jsonLocationPath = "/test-data/authentication/loginMissingEmailAndPassword.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] loginMissingEmail() {
        String jsonLocationPath = "/test-data/authentication/loginMissingEmail.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] loginMissingPassword() {
        String jsonLocationPath = "/test-data/authentication/loginMissingPassword.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }
}
