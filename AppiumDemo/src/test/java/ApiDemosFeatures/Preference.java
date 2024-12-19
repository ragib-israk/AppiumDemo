package ApiDemosFeatures;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Preference {

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
	public void testPreferenceSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Preference Test");
			test.info("Navigating to Preference");

			// Navigate to the Preference section
			driver.findElement(AppiumBy.accessibilityId("Preference")).click();
			test.pass("Successfully navigated to 'Preference'.");
			test.info("Preference is loaded.");

		} catch (Exception e) {
			test.fail("Test Preference section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testPreferenceXMLSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Preferences from XML Test");
			test.info("Navigating to Preferences from XML");

			// 1. Preferences from XML
			driver.findElement(AppiumBy.accessibilityId("1. Preferences from XML")).click();
			driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[1]"))
					.click();
			driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[2]"))
					.click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Preferences from XML'.");
			test.info("Preferences from XML is loaded.");

		} catch (Exception e) {
			test.fail("Test Preferences from XML section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testLaunchingPreferencesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Launching preferences Test");
			test.info("Navigating to Launching preferences");

			// 2. Launching preferences
			driver.findElement(AppiumBy.accessibilityId("2. Launching preferences")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"LAUNCH PREFERENCEACTIVITY\"]")).click();
			driver.navigate().back();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Launching preferences'.");
			test.info("Launching preferences is loaded.");

		} catch (Exception e) {
			test.fail("Test Launching preferences section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testPreferenceDependenciesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Preference dependencies Test");
			test.info("Navigating to Preference dependencies");

			// 3. Preference dependencies
			driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"]")).click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Preference dependencies'.");
			test.info("Preference dependencies is loaded.");

		} catch (Exception e) {
			test.fail("Test Preference dependencies section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testDefaultValuesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Default values Test");
			test.info("Navigating to Default values");

			// 4. Default values
			driver.findElement(AppiumBy.accessibilityId("4. Default values")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
			driver.navigate().back();
			test.pass("Successfully navigated to 'Default values'.");
			test.info("Default values is loaded.");
			
		} catch (Exception e) {
			test.fail("Test Default values section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testPreferencesFromCodeSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Preferences from code Test");
			test.info("Navigating to Preferences from code");

			// 5. Preferences from code
			driver.findElement(AppiumBy.accessibilityId("5. Preferences from code")).click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Checkbox preference\"]"))
					.click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Switch preference\"]"))
					.click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[3]/android.widget.RelativeLayout"))
					.click();
			WebElement animal = driver
					.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"android:id/edit\"]"));
			animal.sendKeys("Tiger");
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[4]/android.widget.RelativeLayout"))
					.click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Beta Option 02\"]"))
					.click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Preferences from code'.");
			test.info("Preferences from code is loaded.");

		} catch (Exception e) {
			test.fail("Test Preferences from code section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testAdvancedPreferencesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Advanced preferences Test");
			test.info("Navigating to Advanced preferences");

			// 6. Advanced preferences
			driver.findElement(AppiumBy.accessibilityId("6. Advanced preferences")).click();
			driver.navigate().back();

			test.pass("Successfully navigated to 'Advanced preferences'.");
			test.info("Advanced preferences is loaded.");
			
		} catch (Exception e) {
			test.fail("Test Advanced preferences section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testFragmentSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Fragment Test");
			test.info("Navigating to Fragment");

			// 7. Fragment
			driver.findElement(AppiumBy.accessibilityId("7. Fragment")).click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Checkbox preference\"]"))
					.click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]/android.widget.RelativeLayout"))
					.click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]")).click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[3]/android.widget.RelativeLayout"))
					.click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Charlie Option 03\"]"))
					.click();
			driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[2]"))
					.click();
			driver.findElement(AppiumBy.xpath("(//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"])[3]"))
					.click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Fragment'.");
			test.info("Fragment is loaded.");

		} catch (Exception e) {
			test.fail("Test Fragment section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testHeadersSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Headers Test");
			test.info("Navigating to Headers");

			// 8. Headers
			driver.findElement(AppiumBy.accessibilityId("8. Headers")).click();
			driver.findElement(AppiumBy.xpath(
					"//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[3]/android.widget.RelativeLayout"))
					.click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"android:id/button2\"]")).click();
			driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"SOME ACTION\"]")).click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Headers'.");
			test.info("Headers is loaded.");

		} catch (Exception e) {
			test.fail("Test Headers section failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testSwitchSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Switch Test");
			test.info("Navigating to Switch");

			// 9. Switch
			driver.findElement(AppiumBy.accessibilityId("9. Switch")).click();
			driver.findElement(
					AppiumBy.xpath("(//android.widget.LinearLayout[@resource-id=\"android:id/widget_frame\"])[1]"))
					.click();
			driver.findElement(AppiumBy
					.xpath("//android.widget.Switch[@resource-id=\"android:id/switch_widget\" and @text=\"OFF\"]"))
					.click();
			driver.findElement(AppiumBy
					.xpath("//android.widget.Switch[@resource-id=\"android:id/switch_widget\" and @text=\"YES\"]"))
					.click();
			driver.navigate().back();
			
			test.pass("Successfully navigated to 'Switch'.");
			test.info("Switch is loaded.");

		} catch (Exception e) {
			test.fail("Test Switch section failed: " + e.getMessage().split("\n")[0]);
		}

		Thread.sleep(2000);
		System.out.println("Session closed");
		driver.quit();

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}
}
