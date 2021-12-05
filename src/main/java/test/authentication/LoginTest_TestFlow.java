package test.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.LoginCreds;
import test_data.authentication.DataObjectBuilder;
import test_flows.authentication.LoginFlow;

public class LoginTest_TestFlow extends BaseTest {
    @TmsLink("TS_1234")
    @Description("Test login with data driven...")
    @Test(dataProvider = "loginValidCredsData", description = "Login test with valid credential", priority = 2)
    public void loginWithValidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver);
        loginFlow
                .accessLoginForm()
                .login(loginCreds)
                .verifyLoginSuccess()
                .clickOK();
    }

    @TmsLink("TS_5678")
    @Description("Test login with data driven...")
    @Test(dataProvider = "loginInvalidCredsData", description = "Login test with invalid credential", priority = 1)
    public void loginWithInvalidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        LoginFlow loginFlow = new LoginFlow(appiumDriver);
        loginFlow
                .accessLoginForm()
                .login(loginCreds)
                .verifyLoginFail();
    }

    @DataProvider
    public LoginCreds[] loginValidCredsData() {
        String jsonLocationPath = "/src/main/resources/test-data/authentication/loginValidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }

    @DataProvider
    public LoginCreds[] loginInvalidCredsData() {
        String jsonLocationPath = "/src/main/resources/test-data/authentication/loginInvalidCreds.json";
        return DataObjectBuilder.buildDataObject(jsonLocationPath, LoginCreds[].class);
    }
}
