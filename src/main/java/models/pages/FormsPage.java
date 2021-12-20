package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.forms.ActiveButtonDialogComponent;
import models.components.forms.FormsComponent;
import models.components.global.BottomNavComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormsPage extends GeneralPage {
    private final AppiumDriver<MobileElement> appiumDriver;
    private FormsComponent formsComponent;

    private static final By inputtedFieldSel = MobileBy.AccessibilityId("input-text-result");
    private static final By switchTextSel = MobileBy.AccessibilityId("switch-text");
    private static final By dropdownTextSel = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/*/android.widget.EditText");

    public FormsPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
        formsComponent = new FormsComponent(appiumDriver);
    }

    @Step("Type input field as {text}")
    public FormsPage inputField(String text) {
        formsComponent.inputField(text);
        return this;
    }

    @Step("Toggle switch")
    public FormsPage toggleSwitch() {
        formsComponent.toggleSwitch();
        return this;
    }

    @Step("Select dropdown with option {option}")
    public FormsPage selectDropdown(String option) {
        formsComponent.selectDropdown(option);
        return this;
    }

    @Step("Click Active button")
    public FormsPage clickActiveButton() {
        formsComponent.clickActiveButton();
        return this;
    }

    public boolean getTextInputtedDisplayed() {
        return appiumDriver.findElement(inputtedFieldSel).isDisplayed();
    }

    public boolean getSwitchTextDisplayed() {
        return appiumDriver.findElement(switchTextSel).isDisplayed();
    }

    public boolean getDropdownTextDisplayed() {
        return appiumDriver.findElement(dropdownTextSel).isDisplayed();
    }

    public String getTextInputted() {
        return appiumDriver.findElement(inputtedFieldSel).getText();
    }

    public String getSwitchText() {
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 5);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(switchTextSel));
        return appiumDriver.findElement(switchTextSel).getText();
    }

    public String getDropdownText() {
        return appiumDriver.findElement(dropdownTextSel).getText();
    }

    public BottomNavComponent bottomNavigation() {
        return new BottomNavComponent(appiumDriver);
    }

    public ActiveButtonDialogComponent activeButtonDialog() {
        return new ActiveButtonDialogComponent(appiumDriver);
    }
}
