package models.components.home;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HomePageComponent {
    private final AppiumDriver<MobileElement> appiumDriver;

    public HomePageComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }
}
