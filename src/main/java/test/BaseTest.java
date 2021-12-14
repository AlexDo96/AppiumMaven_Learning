package test;

import driver.DriverFactory_Ex;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
    // Thread-safe
    // 1. synchronizedList
    // LocalThread | isolate appium threads
    private final static List<DriverFactory_Ex> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory_Ex> driverThread;
    private String udid;
    private String port;
    private String systemPort;

    /*
    // 2. ThreadLocal.withInitial(Supplier<? extends S> supplier)
    // Supplier is Functional Interface => Using Lambda expression to return driverThread instance
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory_Ex driverThread = new DriverFactory_Ex();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        for (DriverFactory_Ex webDriverThread : driverThreadPool) {
            webDriverThread.quitAppiumSession();
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver();
    }
    */

    @BeforeTest(alwaysRun = true)
    @Parameters({"udid", "port", "systemPort"})
    public void beforeTest(String udid, String port, String systemPort) {
        System.out.println(udid + "|" + port + "|" + systemPort);
        this.udid = udid;
        this.port = port;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory_Ex driverThread = new DriverFactory_Ex();
            driverThreadPool.add(driverThread);
            return driverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driverThread.get().quitAppiumSession();
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getAppiumDriver(udid, port, systemPort);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get the test Method name
            String testMethodName = result.getName();

            // 2. Declare the file location & Declare the file name
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String dateTaken = y + "-" + (m + 1) + "-" + d + "-" + hr + "-" + min + "-" + sec;
            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + testMethodName + "_" + dateTaken + ".png";

            // 3. Save the screenshot to the system
            File screenShot = driverThread.get().getAppiumDriver().getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShot, new File(fileLocation));
                Path content = Paths.get(fileLocation);
                try (InputStream is = Files.newInputStream(content)) {
                    Allure.addAttachment(testMethodName, is);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
