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
import test_flows.authentication.SignupFlow;

public class SignUpTest_TestParallelFlow extends BaseTest_Parallel {
    @TmsLink("TS_1234")
    @Description("Test sign up with data driven...")
    @Test(dataProvider = "loginValidCredsData", description = "Signup test with valid credential")
    public void signupWithValidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        SignupFlow signupFlow = new SignupFlow(appiumDriver, loginCreds);
        signupFlow
                .accessLoginForm()
                .switchToSignUpTab()
                .signup()
                .verifySignUp()
                .clickOK();
    }

    @TmsLink("TS_5678")
    @Description("Test sign up with data driven...")
    @Test(dataProvider = "loginInvalidCredsData", description = "Signup test with invalid credential")
    public void signupWithInvalidCreds(LoginCreds loginCreds) {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Switch to Sign Up tab and register an account
        SignupFlow signupFlow = new SignupFlow(appiumDriver, loginCreds);
        signupFlow
                .accessLoginForm()
                .switchToSignUpTab()
                .signup()
                .verifySignUp();
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
