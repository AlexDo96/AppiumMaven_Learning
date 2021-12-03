package test;

import driver.DriverFactory_Ex;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseTest {
    // Thread-safe
    // 1. synchronizedList
    // LocalThread | isolate appium threads
    private final static List<DriverFactory_Ex> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory_Ex> driverThread;


    // 2. ThreadLocal.withInitial(Supplier<? extends S> supplier)
    // Supplier is Functional Interface => Using Lambda expression to return driverThread instance
    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory_Ex driverThread = new DriverFactory_Ex();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public static void afterSuite() {
        for (DriverFactory_Ex webDriverThread : driverThreadPool) {
            webDriverThread.quitAppiumSession();
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
}
