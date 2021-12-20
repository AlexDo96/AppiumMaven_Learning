package test_flows.form;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.FormsPage;
import org.testng.asserts.SoftAssert;

public class FormsFLow {
    private AppiumDriver<MobileElement> appiumDriver;
    private FormsPage formsPage;
    private SoftAssert softAssert;
    private Boolean expectedTextInputtedDisplayed = true;
    private Boolean expectedSwitchTextDisplayed = true;
    private Boolean expectedDropdownTextDisplayed = true;
    private Boolean expectedActiveButtonAlertDisplayed = true;
    private Boolean expectedActiveButtonMessageDisplayed = true;
    private String expectedActiveButtonAlertText = "This button is";
    private String expectedActiveButtonMessageText = "This button is active";

    public FormsFLow(AppiumDriver<MobileElement> appiumDriver) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
    }

    @Step("Home Page initialization")
    public FormsFLow initFormsPage() {
        formsPage = new FormsPage(appiumDriver);
        return this;
    }

    @Step("Access to Login form")
    public FormsFLow accessFormsPage() {
        if (formsPage == null) {
            initFormsPage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = formsPage.bottomNavigation();
        bottomNavComponent.clickOnFormLbl();
        return this;
    }

    @Step("Input text to field with text {text}")
    public FormsFLow inputText(String text) {
        if (formsPage == null) {
            throw new RuntimeException("Please use method accessFormsPage first");
        }

        formsPage
                .inputField(text);
        return this;
    }

    @Step("Make sure user input can be displayed")
    public FormsFLow checkUserInputDisplayed(String expectedTextInputted) {
        Boolean actualTextInputtedDisplayed = formsPage.getTextInputtedDisplayed();
        softAssert.assertEquals(actualTextInputtedDisplayed, expectedTextInputtedDisplayed, "[ERR] User text inputted is not displayed !!");
        String actualTextInputted = formsPage.getTextInputted();
        softAssert.assertEquals(actualTextInputted, expectedTextInputted, "[ERR] User text inputted incorrect !!");
        return this;
    }

    @Step("Toggle switch ON")
    public FormsFLow toggleSwitchON() {
        if (formsPage == null) {
            throw new RuntimeException("Please use method accessFormsPage first");
        }

        formsPage
                .toggleSwitch();
        return this;
    }

    @Step("Toggle switch OFF")
    public FormsFLow toggleSwitchOFF() {
        if (formsPage == null) {
            throw new RuntimeException("Please use method accessFormsPage first");
        }

        formsPage
                .toggleSwitch();
        return this;
    }

    @Step("Make sure user can switch on/off and text displayed")
    public FormsFLow checkToggleSwitchDisplayed(String expectedSwitch) {
        Boolean actualSwitchTextDisplayed = formsPage.getSwitchTextDisplayed();
        softAssert.assertEquals(actualSwitchTextDisplayed, expectedSwitchTextDisplayed, "[ERR] Switch text is not displayed !!");
        String actualSwitchText = formsPage.getSwitchText();
        softAssert.assertEquals(actualSwitchText, "Click to turn the switch " + expectedSwitch, "[ERR] Switch text incorrect !!");
        return this;
    }

    @Step("Select dropdown value {option}")
    public FormsFLow selectDropdown(String option) {
        if (formsPage == null) {
            throw new RuntimeException("Please use method accessFormsPage first");
        }

        formsPage
                .selectDropdown(option);
        return this;
    }

    @Step("Make sure user can switch on/off and text displayed")
    public FormsFLow checkDropdownDisplayed(String option) {
        Boolean actualDropdownTextDisplayed = formsPage.getDropdownTextDisplayed();
        softAssert.assertEquals(actualDropdownTextDisplayed, expectedDropdownTextDisplayed, "[ERR] Dropdown text is not displayed !!");
        String actualDropdownText = formsPage.getDropdownText();
        System.out.println("Actual : " + actualDropdownText);
        switch (option) {
            case "2":
                softAssert.assertEquals(actualDropdownText, "webdriver.io is awesome", "[ERR] Dropdown text incorrect !!");
                break;
            case "3":
                softAssert.assertEquals(actualDropdownText, "Appium is awesome", "[ERR] Dropdown text incorrect !!");
                break;
            case "4":
                softAssert.assertEquals(actualDropdownText, "This app is awesome", "[ERR] Dropdown text incorrect !!");
                break;
            default:
                throw new RuntimeException("[ERR] Dropdown text incorrect !!");
        }
        return this;
    }

    @Step("Swipe up and click Active button")
    public FormsFLow clickActiveButton() {
        if (formsPage == null) {
            throw new RuntimeException("Please use method accessFormsPage first");
        }

        formsPage
                .clickActiveButton();
        return this;
    }

    @Step("Make sure Active/Inactive button works properly")
    public FormsFLow checkActiveButtonDialogDisplayed() {
        Boolean actualActiveButtonAlertDisplayed = formsPage.activeButtonDialog().getDialogAlertDisplayed();
        softAssert.assertEquals(actualActiveButtonAlertDisplayed, expectedActiveButtonAlertDisplayed, "[ERR] Active button dialog is not displayed !!");
        Boolean actualActiveButtonMessageDisplayed = formsPage.activeButtonDialog().getDialogMessageDisplayed();
        softAssert.assertEquals(actualActiveButtonMessageDisplayed, expectedActiveButtonMessageDisplayed, "[ERR] Active button message is not displayed !!");
        String actualActiveButtonAlertText = formsPage.activeButtonDialog().dialogAlertTitle();
        softAssert.assertEquals(actualActiveButtonAlertText, expectedActiveButtonAlertText, "[ERR] Active button dialog text incorrect !!");
        String actualActiveButtonMessageText = formsPage.activeButtonDialog().dialogMessageTitle();
        softAssert.assertEquals(actualActiveButtonMessageText, expectedActiveButtonMessageText, "[ERR] Active button message text incorrect !!");
        return this;
    }

    @Step("Clik OK on Active button dialog")
    public FormsFLow clickOKActiveButtonDialog() {
        formsPage
                .activeButtonDialog().clickOK();
        return this;
    }
}
