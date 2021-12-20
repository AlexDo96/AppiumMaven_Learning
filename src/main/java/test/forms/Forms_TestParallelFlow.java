package test.forms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import test.BaseTest_Parallel;
import test_flows.form.FormsFLow;

import java.util.Random;

public class Forms_TestParallelFlow extends BaseTest_Parallel {
    @Description("Test Form Page")
    @Test(description = "Form_001: what user input can be displayed")
    public void checkUserInputDisplayed() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Init user input string
        int length = 8;
        boolean useLetters = true;
        boolean useNumbersForString = false;
        String generatedStringEmail = RandomStringUtils.random(length, useLetters, useNumbersForString);
        String userEmail = generatedStringEmail + "@gmail.com";

        FormsFLow formsFLow = new FormsFLow(appiumDriver);
        formsFLow
                .accessFormsPage()
                .inputText(userEmail)
                .checkUserInputDisplayed(userEmail);
    }

    @Description("Test Form Page")
    @Test(description = "Form_002: user can switch on and text displayed")
    public void checkUserSwitchOn() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Init switch status when user toggle switch to ON
        String switchStatus = "OFF";

        FormsFLow formsFLow = new FormsFLow(appiumDriver);
        formsFLow
                .accessFormsPage()
                .toggleSwitchON()
                .checkToggleSwitchDisplayed(switchStatus);
    }

    @Description("Test Form Page")
    @Test(description = "Form_003: user can switch off and text displayed")
    public void checkUserSwitchOff() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Init switch status when user toggle switch to OFF
        String switchStatus = "ON";

        FormsFLow formsFLow = new FormsFLow(appiumDriver);
        formsFLow
                .accessFormsPage()
                .toggleSwitchOFF()
                .checkToggleSwitchDisplayed(switchStatus);
    }

    @Description("Test Form Page")
    @Test(description = "Form_004: user can select dropdown webdriverio/appium/this app is awesome")
    public void checkUserSelectDropdown() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        // Init select option (Option value selector begin from 2 to 4)
        String option = String.valueOf(new Random().nextInt(4 - 2 + 1) + 2);
        System.out.println("Select option: " + option);

        FormsFLow formsFLow = new FormsFLow(appiumDriver);
        formsFLow
                .accessFormsPage()
                .selectDropdown(option)
                .checkDropdownDisplayed(option);
    }

    @Description("Test Form Page")
    @Test(description = "Form_005: Active/Inactive button works properly")
    public void checkActiveInactiveButton() {
        // Init driver
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        FormsFLow formsFLow = new FormsFLow(appiumDriver);
        formsFLow
                .accessFormsPage()
                .clickActiveButton()
                .checkActiveButtonDialogDisplayed()
                .clickOKActiveButtonDialog();
    }
}
