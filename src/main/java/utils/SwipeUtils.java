package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;

public class SwipeUtils {
    private final AppiumDriver<MobileElement> appiumDriver;
    private Dimension mobileScreenSize;
    private TouchAction touchAction;

    public SwipeUtils(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.mobileScreenSize = appiumDriver.manage().window().getSize();
        this.touchAction = new TouchAction(appiumDriver);
    }

    public void swipeNormal(String percentOfBeginX, String percentOfEndX, String percentOfBeginY, String percentOfEndY) {
        PointOption startPoint = covertStartPoint(percentOfBeginX, percentOfBeginY);
        PointOption endPoint = covertEndPoint(percentOfBeginX, percentOfBeginY, percentOfEndX, percentOfEndY);
        performSwipe(startPoint, endPoint);
    }

    public void swipeUntilFoundElement(String percentOfBeginX, String percentOfEndX, String percentOfBeginY, String percentOfEndY, int swipeTimes, By mobileElem) {
        PointOption startPoint = covertStartPoint(percentOfBeginX, percentOfBeginY);
        PointOption endPoint = covertEndPoint(percentOfBeginX, percentOfBeginY, percentOfEndX, percentOfEndY);
        performSwipeUntil(startPoint, endPoint, swipeTimes, mobileElem);
    }

    private PointOption covertStartPoint(String percentOfBeginX, String percentOfBeginY) {
        // Get Mobile window size
        mobileScreenSize = appiumDriver.manage().window().getSize();
        int screenHeight = mobileScreenSize.getHeight();
        int screenWidth = mobileScreenSize.getWidth();

        // Calculate touch point
        int xStartPoint = Integer.parseInt(percentOfBeginX) * screenWidth / 100;
        int yStartPoint = Integer.parseInt(percentOfBeginY) * screenHeight / 100;

        // Convert to PointOptions - Coordinates
        return new PointOption().withCoordinates(xStartPoint, yStartPoint);
    }

    private PointOption covertEndPoint(String percentOfBeginX, String percentOfBeginY, String percentOfEndX, String percentOfEndY) {
        // Get Mobile window size
        mobileScreenSize = appiumDriver.manage().window().getSize();
        int screenHeight = mobileScreenSize.getHeight();
        int screenWidth = mobileScreenSize.getWidth();

        // Calculate touch point
        int xStartPoint = Integer.parseInt(percentOfBeginX) * screenWidth / 100;
        int yStartPoint = Integer.parseInt(percentOfBeginY) * screenHeight / 100;
        int xEndPoint;
        int yEndPoint;

        if (percentOfEndX != null) {
            xEndPoint = Integer.parseInt(percentOfEndX) * screenWidth / 100;
        } else {
            xEndPoint = xStartPoint;
        }

        if (percentOfEndY != null) {
            yEndPoint = Integer.parseInt(percentOfEndY) * screenWidth / 100;
        } else {
            yEndPoint = yStartPoint;
        }

        // Convert to PointOptions - Coordinates
        return new PointOption().withCoordinates(xEndPoint, yEndPoint);
    }

    private void performSwipe(PointOption startPoint, PointOption endPoint) {
        // Perform Touch actions
        touchAction = new TouchAction(appiumDriver);

        // Swipe up
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                .moveTo(endPoint)
                .release()
                .perform(); // without this, will be no action at all
    }

    private void performSwipeUntil(PointOption startPoint, PointOption endPoint, int swipeTimes, By mobileElem) {
        // Perform Touch actions
        touchAction = new TouchAction(appiumDriver);
        int MAX_SWIPE_TIME = swipeTimes;
        int swipeTime = 0;

        while (swipeTime < MAX_SWIPE_TIME) {
            // Using findElements -> When element is found, put it in List<MobileElement>
            List<MobileElement> matchedElems = appiumDriver.findElements(mobileElem);
            if (!matchedElems.isEmpty()) {
                break;
            }

            // Swipe
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform(); // without this, will be no action at all

            swipeTime++;
        }
    }
}
