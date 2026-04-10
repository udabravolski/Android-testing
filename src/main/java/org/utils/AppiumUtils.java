package org.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppiumUtils {
    public Double getFormattedAmount(String initialAmount) {
        return Double.parseDouble(initialAmount.substring(1));
    }

    public String getScreenshotPath(AppiumDriver driver, String testCaseName) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFileName = System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFileName));
        return destinationFileName;
    }

    public void explicitWaitForCartPage(WebElement cartTitle, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(cartTitle, "text", "Cart"));
    }

}
