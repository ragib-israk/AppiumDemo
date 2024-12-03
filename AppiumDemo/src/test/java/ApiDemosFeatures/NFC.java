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

public class NFC {

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
	public void testNfcSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("NFC Test");
			test.info("Navigating to NFC");

			// Navigate to NFC section
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"NFC\"]")).click();
			test.pass("Successfully navigated to 'NFC'.");
			test.info("NFC is loaded.");

		} catch (Exception e) {
			test.fail("Test NFC section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testForegroundDispatchSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("ForegroundDispatch Test");
			test.info("Navigating to ForegroundDispatch");

			// 1. ForegroundDispatch
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"ForegroundDispatch\"]")).click();
	        driver.navigate().back();
			test.pass("Successfully navigated to 'ForegroundDispatch'.");
			test.info("ForegroundDispatch is loaded.");
			
		} catch (Exception e) {
			test.fail("Test ForegroundDispatch section failed: " + e.getMessage().split("\n")[0]);
		}
	}


			@Test
			public void testForegroundNdefPusheSection() throws InterruptedException, IOException {

				try {
					test = extent.createTest("ForegroundNdefPush Test");
			

			// 2. ForegroundNdefPush
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"ForegroundNdefPush\"]")).click();
			driver.navigate().back();
			test.pass("Successfully navigated to 'ForegroundNdefPush'.");
			test.info("ForegroundNdefPush is loaded.");

		} catch (Exception e) {
			test.fail("Test ForegroundNdefPush section failed: " + e.getMessage().split("\n")[0]);
			
		}
	}

	@Test
	public void testTechFilterSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("TechFilter Test");

			// 3. TechFilter
			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"TechFilter\"]")).click();
			driver.navigate().back();
			driver.navigate().back();
			test.pass("Successfully navigated to 'TechFilter'.");
			test.info("TechFilter is loaded.");

		} catch (Exception e) {
			test.fail("Test TechFilter section failed: " + e.getMessage().split("\n")[0]);
		}

		System.out.println("Session closed");
		driver.quit();

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}
}
