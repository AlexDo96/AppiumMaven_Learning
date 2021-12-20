package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory_Ex {
    private AppiumDriver<MobileElement> appiumDriver;
    private static AppiumDriverLocalService appiumServer;

    public AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver();
        return appiumDriver;
    }

    public AppiumDriver<MobileElement> getAppiumDriver(String udid, String port, String systemPort) {
        if (appiumDriver == null)
            appiumDriver = initAppiumDriver(udid, port, systemPort);
        return appiumDriver;
    }

    private AppiumDriver<MobileElement> initAppiumDriver() {
        AppiumServiceBuilder appiumServerBuilder = new AppiumServiceBuilder();
        appiumServerBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload"); // Manage Chrome driver automatically
        appiumServerBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServerBuilder);
        appiumServer.start();

        // Setup DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
        // desiredCapabilities.setCapability("avd", "android_27"); // Automatically launch android virtual device - "android_27": avd name
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);
        appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        return appiumDriver;
    }

    // Note: This one is infrastructure for parallel testing
    private AppiumDriver<MobileElement> initAppiumDriver(String udid, String port, String systemPort) {
        // Setup DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, udid);
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.SYSTEM_PORT, Integer.parseInt(systemPort));
        // desiredCapabilities.setCapability("mjpegServerPort", Integer.parseInt(port));
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        String localAppium = System.getenv("localAppium");
        String hub = System.getProperty("hub");

        String targetServer = null;
        if (localAppium != null) {
            targetServer = localAppium + "/wd/hub";
        } else if (hub != null) {
            targetServer = hub + ":4444/wd/hub";
        } else {
            throw new IllegalArgumentException("Please provide localAppium/hub");
        }

        try {
            URL appiumServerPath = new URL(targetServer);
            appiumDriver = new AndroidDriver<>(appiumServerPath, desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appiumDriver;
    }

    public void quitAppiumSession() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;

            // stopAppiumServer(); // Note: When we have final infrastructure for parallel testing, this one is not necessary
        }
    }

    public static void stopAppiumServer() {
        String killNodeWindowsCmd = "taskkill /F /IM node.exe";
        String killNodeLinuxCmd = "killall node";

        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWindowsCmd : killNodeLinuxCmd;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
