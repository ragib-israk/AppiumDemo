package ApiDemosFeatures;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Accessibility {

	private AndroidDriver driver;
	private ExtentReports extent;
	private ExtentTest test;

	@BeforeClass
	public void setup() throws MalformedURLException {
		// Setup ExtentReports
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("Html_report");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Appium Automation with ExtentReports");
		extent.attachReporter(spark);

		// Log setup in the report
		test = extent.createTest("Appium Test Setup");
		test.info("Setting up Desired Capabilities");

		// Setup Desired Capabilities for Appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Xiaomi Redmi Note 5");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("platformVersion", "9");
		capabilities.setCapability("appPackage", "io.appium.android.apis");
		capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");

		// Start Appium session
		URL url = URI.create("http://127.0.0.1:4723/").toURL();
		driver = new AndroidDriver(url, capabilities);

		test.pass("Appium session started successfully");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test
	public void testAccessibilitySection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Accessibility Test");
			test.info("Navigating to Accessibility");

			// Navigate to the Accessibility section
			driver.findElement(AppiumBy.accessibilityId("Accessibility")).click();
			test.pass("Successfully navigated to 'Accessibility'.");
			test.info("Accessibility is loaded.");

		} catch (Exception e) {
			test.fail("Test Accessibility failed: " + e.getMessage().split("\n")[0]);

		}
	}

	@Test
	public void testAccessibilitySectionSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Accessibility Section Test");
			test.info("Navigating to Accessibility section");

			// 1. Accessibility Node Provider
			driver.findElement(AppiumBy.accessibilityId("Accessibility Node Provider")).click();
			Thread.sleep(2000); // Wait to observe
			driver.navigate().back();
			test.pass("Successfully navigated to 'Accessibility Node Provider'.");
			test.info("Accessibility Node Provider is loaded.");

			// 2. Accessibility Node Querying
			driver.findElement(AppiumBy.accessibilityId("Accessibility Node Querying")).click();
			Thread.sleep(2000); // Wait to observe
			driver.navigate().back();
			test.pass("Successfully navigated to 'Accessibility Node Querying'.");
			test.info("Accessibility Node Querying is loaded.");
			

			// 3. Accessibility Service
			driver.findElement(AppiumBy.accessibilityId("Accessibility Service")).click();
			Thread.sleep(2000); // Wait to observe
			driver.navigate().back();
			test.pass("Successfully navigated to 'Accessibility Service'.");
			test.info("Accessibility Service is loaded.");
			

			// 4. Custom View
			driver.findElement(AppiumBy.accessibilityId("Custom View")).click();
			Thread.sleep(2000); // Wait to observe
			driver.navigate().back();
			test.pass("Successfully navigated to 'Custom View'.");
			test.info("Custom View is loaded.");
			

		} catch (Exception e) {
			test.fail("Test Accessibility section failed: " + e.getMessage().split("\n")[0]);
		}

		Thread.sleep(2000);
		System.out.println("Session closed");
		driver.quit();

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}
}
