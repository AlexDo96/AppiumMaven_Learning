package driver;

import caps.MobileCapabilityTypeEx;
import flags.AndroidServerFlagEx;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer() {
        AppiumServiceBuilder appiumServerBuilder = new AppiumServiceBuilder();
        appiumServerBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload"); // Manage Chrome driver automatically
        appiumServerBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
        appiumServer = AppiumDriverLocalService.buildService(appiumServerBuilder);
        appiumServer.start();
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

    public static AndroidDriver<MobileElement> getAndroidDriver() {
        // Setup DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, "emulator-5554");
        // desiredCapabilities.setCapability("avd", "android_27"); // Automatically launch android virtual device - "android_27": avd name
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
        desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, 120);

        androidDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
        androidDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
        return androidDriver;
    }
}
