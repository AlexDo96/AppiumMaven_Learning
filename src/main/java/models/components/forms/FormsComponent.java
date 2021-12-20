package models.components.forms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.SwipeUtils;

public class FormsComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private final SwipeUtils swipeUtils;
    private static final By inputFieldSel = MobileBy.AccessibilityId("text-input");
    private static final By switchSel = MobileBy.AccessibilityId("switch");
    private static final By dropdownSel = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
    private static final By activeButtonSel = MobileBy.AccessibilityId("button-Active");

    public FormsComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        swipeUtils = new SwipeUtils(appiumDriver);
    }

    @Step("Input to field {input}")
    public FormsComponent inputField(String input) {
        appiumDriver.findElement(inputFieldSel).sendKeys(input);
        return this;
    }

    @Step("Toggle switch")
    public FormsComponent toggleSwitch() {
        appiumDriver.findElement(switchSel).click();
        return this;
    }

    @Step("Select dropdown option {option}")
    public FormsComponent selectDropdown(String option) {
        appiumDriver.findElement(dropdownSel).click();
        WebDriverWait explicitWait = new WebDriverWait(appiumDriver, 15);
        final By dropdownDialogSel = MobileBy.xpath("//*/android.widget.CheckedTextView[" + option + "]");
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(dropdownDialogSel));
        appiumDriver.findElement(dropdownDialogSel).click();
        return this;
    }

    @Step("Swipe up and click Active button")
    public FormsComponent
    clickActiveButton() {
        // Ensure whether we are on Forms screen
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElement(switchSel)));
        swipeUtils.swipeNormal("50",null, "90", "20");
        appiumDriver.findElement(activeButtonSel).click();
        return this;
    }
}
