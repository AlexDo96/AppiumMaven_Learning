package models.components.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class WebviewPageComponent {
    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By navToggleBtnSel = MobileBy.cssSelector(".navbar__toggle");
    private static final By menuItemsSel = MobileBy.cssSelector(".menu__list-item a");

    public WebviewPageComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Step("Click Navigation Toggle Button")
    public WebviewPageComponent clickNavigationToggleBtn() throws InterruptedException {
        Thread.sleep(2000);
        appiumDriver.getContextHandles().forEach(context -> { // Lambda expression
            System.out.println("Here are context: " + context);
        });

        // Webview context
        appiumDriver.context("WEBVIEW_com.wdiodemoapp");
        appiumDriver.findElement(navToggleBtnSel).click();
        return this;
    }

    @Step("List all menu items list")
    public WebviewPageComponent listAllMenuItems() {
        List<MobileElement> menuItems = (List<MobileElement>) appiumDriver.findElement(menuItemsSel);
        List<MenuItem> menuItemList = new ArrayList<>();

        for (MobileElement menuItem : menuItems) {
            String menuText = menuItem.getText();
            String menuHyperLink = menuItem.getAttribute("href");

            if (StringUtils.isEmpty(menuText)) {
                menuItemList.add(new MenuItem("GitHub", menuHyperLink));
            } else {
                menuItemList.add(new MenuItem(menuText, menuHyperLink));
            }
        }

        // List all menu items list
        menuItemList.forEach(System.out::println); // Lambda expression
        return this;
    }
}
