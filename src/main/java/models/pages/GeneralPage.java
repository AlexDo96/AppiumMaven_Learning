package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;

public class GeneralPage {
    private final AppiumDriver<MobileElement> appiumDriver;

    public GeneralPage(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public BottomNavComponent bottomNavComp(){
        return new BottomNavComponent(appiumDriver);
    }
}
