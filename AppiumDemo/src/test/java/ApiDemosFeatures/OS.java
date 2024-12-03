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

public class OS {

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
	public void testOSSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("OS Section Test");
			test.info("Navigating to OS section");

			// Navigate to OS section
			driver.findElement(AppiumBy.accessibilityId("OS")).click();
			test.pass("Successfully navigated to 'OS'.");
			test.info("OS Code Messaging is loaded.");

		} catch (Exception e) {
			test.fail("Test OS failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testMorseCodeSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Morse Code Test");

			// Perform actions in OS section
			test.info("Performing Morse Code test");
			driver.findElement(AppiumBy.accessibilityId("Morse Code")).click();
			driver.findElement(AppiumBy.accessibilityId("Vibrate")).click();
			Thread.sleep(2000);
			driver.navigate().back();
			test.pass("Successfully navigated to 'Morse Code'.");
			test.info("Morse Code Messaging is loaded.");
			
			test.pass("Successfully navigated to 'Vibrate'.");
			test.info("Vibrate Messaging is loaded.");


		} catch (Exception e) {
			test.fail("Test Morse Code failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testRotationVectorSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Rotation Vector Test");

			test.info("Rotation Vector test");
			driver.findElement(AppiumBy.accessibilityId("Rotation Vector")).click();
			Thread.sleep(2000);
			driver.navigate().back();
			test.pass("Successfully navigated to 'Rotation Vector'.");
			test.info("Rotation Vector is loaded.");

		} catch (Exception e) {
			test.fail("Test Rotation Vector failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testSensorstestSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Sensors Test");

			test.info("Sensors test");
			driver.findElement(AppiumBy.accessibilityId("Sensors")).click();
			Thread.sleep(2000);
			driver.navigate().back();
			test.pass("Successfully navigated to 'Sensors'.");
			test.info("Sensors is loaded.");

		} catch (Exception e) {
			test.fail("Test Sensors failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testSMSMessagingSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("SMS Messaging Test");

			test.info("SMS Messaging test");
			driver.findElement(AppiumBy.accessibilityId("SMS Messaging")).click();
			driver.findElement(AppiumBy.accessibilityId("Enable SMS broadcast receiver")).click();
			driver.findElement(AppiumBy.accessibilityId("Send")).click();
			Thread.sleep(2000);
			driver.navigate().back();
			driver.navigate().back();
			test.pass("Successfully navigated to 'SMS Messaging'.");
			test.info("SMS Messaging is loaded.");
			
			test.pass("Successfully navigated to 'Enable SMS broadcast receiver'.");
			test.info("Enable SMS broadcast receiver is loaded.");
			
			test.pass("Successfully navigated to 'Send'.");
			test.info("Send is loaded.");
			

		} catch (Exception e) {
			test.fail("Test SMS Messaging  failed: " + e.getMessage().split("\n")[0]);
		}
		
		

		Thread.sleep(2000);
		System.out.println("Session closed");
		driver.quit();

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}
}
