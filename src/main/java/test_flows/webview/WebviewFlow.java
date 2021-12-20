package test_flows.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.global.BottomNavComponent;
import models.pages.WebviewPage;
import org.testng.asserts.SoftAssert;

public class WebviewFlow {
    private AppiumDriver<MobileElement> appiumDriver;
    private WebviewPage webviewPage;
    private SoftAssert softAssert;

    public WebviewFlow(AppiumDriver<MobileElement> appiumDriver) {
        softAssert = new SoftAssert();
        this.appiumDriver = appiumDriver;
    }

    @Step("Webview Page initialization")
    public WebviewFlow initWebviewPage() {
        webviewPage = new WebviewPage(appiumDriver);
        return this;
    }

    @Step("Access to Webview page")
    public WebviewFlow accessWebviewPage() {
        if (webviewPage == null) {
            initWebviewPage();
        }
        // Click Login feature
        BottomNavComponent bottomNavComponent = webviewPage.bottomNavigation();
        bottomNavComponent.clickOnWebviewLbl();
        return this;
    }

    @Step("Click Navigation Toggle")
    public WebviewFlow clickNavigationToggle() throws InterruptedException {
        if (webviewPage == null) {
            throw new RuntimeException("Please use method accessWebviewPage first");
        }
        webviewPage.clickNavigationToggleButton();
        return this;
    }

    @Step("List all Menu items")
    public WebviewFlow listAllMenu() {
        if (webviewPage == null) {
            throw new RuntimeException("Please use method accessWebviewPage first");
        }
        webviewPage.listAllMenuItemsList();
        return this;
    }
}
