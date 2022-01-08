package test;

import caps.MobileCapabilityTypeEx;
import com.google.common.reflect.ClassPath;
import driver.PlatformType;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main implements MobileCapabilityTypeEx {
    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {

        // Get all classes that start with the prefix "test."
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();
        for (final ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith("test.") && !info.getName().equalsIgnoreCase("test.BaseTest_Parallel") && !info.getName().equalsIgnoreCase("test.Main")) {
                testClasses.add(info.load());
            }
        }

        // Get platform
        String platformName = System.getProperty("platform");
        if (platformName == null)
            throw new RuntimeException("Please provide platform");
        try {
            PlatformType.valueOf(platformName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERR] " + platformName + " is not supported, we covered for " + Arrays.toString(PlatformType.values()));
        }

        // Create sublist from test classes into device list | Device list can be fetched from anywhere
        List<String> iPhoneDeviceList = Arrays.asList("iPhone 12", "iPhone 13");
        List<String> androidDeviceList = Arrays.asList("emulator-5554", "emulator-5556");
        List<String> deviceList = platformName.equalsIgnoreCase(PlatformType.ios.getName()) ? iPhoneDeviceList : androidDeviceList;

        int testNumEachDevice = testClasses.size() / deviceList.size();
        HashMap<String, List<Class<?>>> desiredCaps = new HashMap<>();
        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumEachDevice;
            int endIndex = deviceIndex == deviceList.size() - 1 ? testClasses.size() : (startIndex + testNumEachDevice);
            List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
            desiredCaps.put(deviceList.get(deviceIndex), subTestList);
        }

        // Building the test suites
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        // Put all classes into test group divided by device list
        List<XmlTest> allTests = new ArrayList<>();
        for (String deviceName : desiredCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(deviceName);
            List<XmlClass> xmlClasses = new ArrayList<>();

            List<Class<?>> dedicatedClasses = desiredCaps.get(deviceName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }
            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID, deviceName);
            test.addParameter(PLATFORM_NAME, platformName);
            test.addParameter(PLATFORM_VERSION, "15.0");
            test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));

            // This one is unused currently !!
            test.addParameter("port", "0");
            allTests.add(test);
        }

        // Add all tests into the suite
        suite.setTests(allTests);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);

        // Run a group of test
        String targetGroup = args.length != 0 ? args[0] : null;
        if (targetGroup != null)
            suite.addIncludedGroup(targetGroup);

        System.out.println(suite.toXml());

        //Add the suite to the list of suites.
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        // Invoke run() method
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
