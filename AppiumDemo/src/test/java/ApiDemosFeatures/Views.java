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

public class Views {

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
			test = extent.createTest("Views Section Test");
			test.info("Navigating to Views section");

			// Navigate to the View section
			driver.findElement(AppiumBy.accessibilityId("Views")).click();
			test.pass("Successfully navigated to 'Views' section.");
			Thread.sleep(3000);

			// Navigate to Animation
			driver.findElement(AppiumBy.accessibilityId("Animation")).click();
			test.pass("Successfully navigated to 'Animation' section.");
			test.info("Animation section is loaded.");

			// 1. 3D Transition
			driver.findElement(AppiumBy.accessibilityId("3D Transition")).click();
			test.pass("Successfully opened '3D Transition'.");
			test.info("3D Transition section is loaded.");

			// 2. Lyon
			driver.findElement(
					AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Lyon\"]"))
					.click();
			driver.findElement(
					AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"io.appium.android.apis:id/picture\"]"))
					.click();
			test.pass("Successfully interacted with 'Lyon' animation.");

			// 3. Livermore
			driver.findElement(AppiumBy
					.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Livermore\"]"))
					.click();
			driver.findElement(
					AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"io.appium.android.apis:id/picture\"]"))
					.click();
			test.pass("Successfully interacted with 'Livermore' animation.");

			// 4. Bodie
			driver.findElement(
					AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Bodie\"]"))
					.click();
			driver.findElement(
					AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"io.appium.android.apis:id/picture\"]"))
					.click();
			test.pass("Successfully interacted with 'Bodie' animation.");

			// 5. Lake Tahoe
			driver.findElement(AppiumBy
					.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Lake Tahoe\"]"))
					.click();
			driver.findElement(
					AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"io.appium.android.apis:id/picture\"]"))
					.click();
			driver.navigate().back();
			test.pass("Successfully interacted with 'Lake Tahoe' animation and navigated back.");

			// 6. Interpolators
			driver.findElement(AppiumBy.accessibilityId("Interpolators")).click();
			test.pass("Successfully navigated to 'Interpolators' section.");
			driver.findElement(
					AppiumBy.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner\"]"))
					.click();
			test.info("Opened the Spinner menu in 'Interpolators'.");
			driver.findElement(AppiumBy.xpath(
					"//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Accelerate\"]"))
					.click();
			test.pass("Selected 'Accelerate' from the Spinner options in 'Interpolators'.");
			driver.navigate().back();
			test.info("Navigated back after interacting with 'Interpolators'.");
			
			

			// 7. Push (including Push Left)
			driver.findElement(AppiumBy.accessibilityId("Push")).click();
			test.pass("Successfully navigated to 'Push' section.");
			driver.findElement(
					AppiumBy.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner\"]"))
					.click();
			test.info("Opened the Spinner menu in 'Push'.");
			driver.findElement(AppiumBy.xpath(
					"//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Push left\"]"))
					.click();
			test.pass("Selected 'Push left' from the Spinner options in 'Push'.");
			driver.navigate().back();
			test.info("Navigated back after interacting with 'Push'.");
			
			

			// 8. Shake
			driver.findElement(AppiumBy.accessibilityId("Shake")).click();
			test.pass("Successfully navigated to 'Shake' section.");

//          System.out.println("Successfully navigated to 'Shake'.");

			driver.findElement(AppiumBy.accessibilityId("Login")).click();
			test.pass("Successfully clicked on 'LOGIN' in the 'Shake' section.");
			driver.navigate().back();
			test.info("Navigated back after interacting with 'Shake'.");
			driver.navigate().back();
			test.info("Returned to the previous screen after completing 'Shake'.");

		} catch (Exception e) {
			test.fail("Test failed: " + e.getMessage().split("\n")[0]);
		}

	}

	@Test
	public void testAutoCompleteSection() throws InterruptedException, IOException {
		test = extent.createTest("Auto Complete Section Test");
		try {

//2.part    // 9.Navigate to ("Auto Complete ")
			driver.findElement(AppiumBy.accessibilityId("Auto Complete")).click();
			test.pass("Successfully navigated to 'Auto Complete' section.");

			// .Screen Top
			driver.findElement(AppiumBy.accessibilityId("1. Screen Top")).click();// Handle "Screen Top" Feature
			test.pass("Successfully navigated to 'Screen Top' feature in 'Auto Complete'.");

			// Enter country in Entry country field
			WebElement country = driver.findElement(
					AppiumBy.xpath("//android.widget.EditText[@resource-id=\"io.appium.android.apis:id/edit\"]"));
			country.sendKeys("Bangladesh");
			test.info("Entered 'Bangladesh' in the 'Enter country' field.");
//          System.out.println("Entered country.");

			// Generate a Key
			WebElement generateButton = driver
					.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Give me Focus\"]"));
			generateButton.click();

			test.pass("Clicked on the 'Generate' button.");
//          System.out.println("Clicked on Generate button.");

			Thread.sleep(2000); // Wait for key generation to complete
			test.info("Waited for key generation to complete.");
			driver.navigate().back();
			test.pass("Navigated back from 'Auto Complete' section.");

			// 10.Scroll
			driver.findElement(AppiumBy.accessibilityId("3. Scroll")).click();
			test.pass("Successfully navigated to 'Scroll' section.");

			driver.findElement(AppiumBy.xpath("(//android.widget.Button[@content-desc=\"Scroll\"])[1]")).click();
			test.pass("Clicked on the 'Scroll' button.");

			driver.navigate().back();
			test.pass("Navigated back from 'Scroll' section.");

			// 11.Multiples items
			driver.findElement(AppiumBy.accessibilityId("6. Multiple items")).click();
			test.pass("Successfully navigated to 'Multiple items' section.");

			driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Give me Focus\"]")).click();
			test.pass("Clicked on the 'Give me Focus' button.");

			driver.navigate().back();
			test.pass("Navigated back from 'Multiple items' section.");
			driver.navigate().back();
			test.pass("Navigated back to the previous section.");

		} catch (Exception e) {
			test.fail("Test Auto Complete Section failed: " + e.getMessage().split("\n")[0]);
		}

	}

	@Test
	public void testButtonsSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Buttons Section Test");

// 3.part  //12.Buttons 

			driver.findElement(AppiumBy.accessibilityId("Buttons")).click();
			test.pass("Successfully navigated to 'Buttons' section.");

			driver.findElement(AppiumBy.accessibilityId("Toggle")).click();
			test.pass("Toggled the button in 'Buttons' section.");

			driver.navigate().back();
			test.pass("Navigated back from 'Buttons' section.");

		} catch (Exception e) {
			test.fail("Test Buttons failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testChronometerSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Chronometer Section Test");

			// 4.part //13.Chronometer
			driver.findElement(AppiumBy.accessibilityId("Chronometer")).click();
			test.pass("Successfully navigated to 'Chronometer' section.");

			driver.findElement(AppiumBy.accessibilityId("Start")).click();
			test.pass("Started the chronometer.");

			driver.findElement(AppiumBy.accessibilityId("Stop")).click();
			test.pass("Stopped the chronometer.");

			driver.findElement(AppiumBy.accessibilityId("Reset")).click();
			test.pass("Reset the chronometer.");

			driver.findElement(AppiumBy.accessibilityId("Set format string")).click();
			test.pass("Set the format string for chronometer.");

			driver.findElement(AppiumBy.accessibilityId("Clear format string")).click();
			test.pass("Cleared the format string for chronometer.");
			driver.navigate().back();

		} catch (Exception e) {
			test.fail("Test Chronometer failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testControlsSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Controls Section Test");

			// 5.part //14.Controls
			driver.findElement(AppiumBy.accessibilityId("Controls")).click();
			test.pass("Successfully navigated to 'Controls' section.");

			driver.findElement(AppiumBy.accessibilityId("1. Light Theme")).click();
			test.pass("Clicked on 'Light Theme'.");

			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@content-desc=\"Checkbox 1\"]")).click();
			test.pass("Checked 'Checkbox 1'.");

			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@content-desc=\"Checkbox 2\"]")).click();
			test.pass("Checked 'Checkbox 2'.");

			driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@content-desc=\"RadioButton 1\"]")).click();
			test.pass("Selected 'RadioButton 1'.");

			driver.findElement(
					AppiumBy.xpath("//android.widget.ToggleButton[@resource-id=\"io.appium.android.apis:id/toggle1\"]"))
					.click();
			test.pass("Toggled 'ToggleButton 1'.");

			driver.findElement(
					AppiumBy.xpath("//android.widget.Spinner[@resource-id=\"io.appium.android.apis:id/spinner1\"]"))
					.click();
			test.pass("Clicked on 'Spinner 1'.");

			driver.findElement(AppiumBy
					.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Jupiter\"]"))
					.click();
			test.pass("Selected 'Jupiter' in spinner.");

			driver.findElement(AppiumBy.xpath("//android.widget.CheckBox[@content-desc=\"Star\"]")).click();
			test.pass("Checked 'Star' checkbox.");

			driver.navigate().back();
			test.pass("Navigated back from 'Controls' section.");
			driver.navigate().back();
			test.pass("Navigated back to the previous section.");

		} catch (Exception e) {
			test.fail("Test Controls failed: " + e.getMessage().split("\n")[0]);
		}

	}

	@Test
	public void testExpandableListsSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Expandable Lists Section Test");

			// 6.part //15.Expandable Lists
			driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
			test.pass("Successfully navigated to 'Expandable Lists' section.");

			driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
			test.pass("Clicked on 'Custom Adapter'.");

			driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Cat Names\"]")).click();
			test.pass("Clicked on 'Cat Names'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Custom Adapter'.");

			driver.findElement(AppiumBy.accessibilityId("3. Simple Adapter")).click();
			test.pass("Clicked on 'Simple Adapter'.");

			driver.findElement(AppiumBy
					.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Group 2\"]"))
					.click();
			test.pass("Clicked on 'Group 2'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Simple Adapter'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Expandable Lists' section.");

		} catch (Exception e) {
			test.fail("Test Expandable Lists failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testGridSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Grid Section Test");

			// 7.part //16.Grid
			driver.findElement(AppiumBy.accessibilityId("Grid")).click();
			test.pass("Successfully navigated to 'Grid' section.");
			driver.findElement(AppiumBy.accessibilityId("1. Icon Grid")).click();
			driver.navigate().back();
			test.pass("Clicked on 'Icon Grid'.");
			test.pass("Navigated back from 'Icon Grid'.");
			driver.findElement(AppiumBy.accessibilityId("2. Photo Grid")).click();
			driver.navigate().back();
			test.pass("Clicked on 'Photo Grid'.");
			test.pass("Navigated back from 'Photo Grid'.");
			driver.findElement(AppiumBy.accessibilityId("3. Selection Mode")).click();
			driver.navigate().back();			
			test.pass("Clicked on 'Selection Mode'.");
			test.pass("Navigated back from 'Selection Mode'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Grid' section.");

		} catch (Exception e) {
			test.fail("Test Grid failed: " + e.getMessage().split("\n")[0]);
		}

	}
          
	// 8.part //17.Drag and Drop

//          //find drag and drop button

// 	       driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click(); //perform click action on view button
// 	       
// 	       WebElement source =  driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
// 	     
// 	       WebElement destination =  driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_2\"]"));
//
// 	       
// 	    //By using touch action class
//	       TouchAction action = new TouchAction(driver);
//
//	   		action.longPress(longPressOptions().withElement(element(source))).moveTo(element(destination)).release().perform();

	@Test
	public void testSearchViewSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Search View Lists Section Test");

			// 9.part //18.Scrolling
			// Scroll to the "TextSwitcher" option
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"));"));
			test.pass("Successfully scrolled to 'TextSwitcher' option.");

			// 10.part //19.Search View
			driver.findElement(AppiumBy.accessibilityId("Search View")).click();
			test.pass("Successfully navigated to 'Search View'.");

			driver.findElement(AppiumBy.accessibilityId("Filter")).click();
			test.pass("Clicked on 'Filter'.");

			WebElement search = driver.findElement(
					AppiumBy.xpath("//android.widget.EditText[@resource-id=\"android:id/search_src_text\"]"));
			search.sendKeys("Airag");
			test.pass("Entered 'Airag' into search field.");

			driver.navigate().back();
			test.pass("Navigated back from 'Search View'.");

			driver.navigate().back();
			test.pass("Navigated back to previous section.");

		} catch (Exception e) {
			test.fail("Test Search View failed: " + e.getMessage().split("\n")[0]);
		}
	}
//
	@Test
	public void testSecureViewSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Secure View Lists Section Test");

			// 11.part //20.Secure View
			driver.findElement(AppiumBy.accessibilityId("Secure View")).click();
			test.pass("Successfully navigated to 'Secure View'.");

			driver.findElement(AppiumBy.accessibilityId("Pop toast")).click();
			test.pass("Clicked on 'Pop toast'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Secure View' section.");

		} catch (Exception e) {
			test.fail("Test Secure View failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testSwitchesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Switches Lists Section Test");

			// 12.part //21.Switches
			driver.findElement(AppiumBy.accessibilityId("Switches")).click();
			test.pass("Successfully navigated to 'Switches'.");

			driver.findElement(AppiumBy.accessibilityId("Default is on")).click();
			test.pass("Clicked on 'Default is on' switch.");

			driver.findElement(AppiumBy.accessibilityId("Monitored switch")).click();
			test.pass("Clicked on 'Monitored switch'.");

			driver.findElement(AppiumBy.accessibilityId(
					"This is an example of a switch with a lot of text in it. It may end up wrapping to another line. The switch will be vertically centered."))
					.click();
			test.pass("Clicked on the long text switch.");

			driver.navigate().back();
			test.pass("Navigated back from 'Switches' section.");

		} catch (Exception e) {
			test.fail("Test Switches failed: " + e.getMessage().split("\n")[0]);
	}
	}
	@Test
	public void testTabsSection() throws InterruptedException, IOException {

		try {

			test = extent.createTest("Tabs Lists Section Test");

			// 13.part //22.Tabs
			driver.findElement(AppiumBy.accessibilityId("Tabs")).click();
			test.pass("Successfully navigated to 'Tabs'.");

			driver.findElement(AppiumBy.accessibilityId("1. Content By Id")).click();
			test.pass("Clicked on 'Content By Id' tab.");

			driver.findElement(AppiumBy.xpath(
					"//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]/android.widget.LinearLayout[2]"))
					.click();
			test.pass("Clicked on the second tab.");

			driver.findElement(AppiumBy.xpath(
					"//android.widget.TabWidget[@resource-id=\"android:id/tabs\"]/android.widget.LinearLayout[3]"))
					.click();
			test.pass("Clicked on the third tab.");

			driver.navigate().back();
			test.pass("Navigated back from 'Tabs' section.");

			driver.navigate().back();
			test.pass("Navigated back to previous section.");

		} catch (Exception e) {
			test.fail("Test Tabs failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testTextClockSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("TextClock Lists Section Test");

			// 14.part //23.TextClock
			driver.findElement(AppiumBy.accessibilityId("TextClock")).click();
			test.pass("Successfully navigated to 'TextClock'.");

			driver.navigate().back();
			test.pass("Navigated back from 'TextClock' section.");

		} catch (Exception e) {
			test.fail("Test TextClock failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testTextSwitcherSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("TextClock Lists Section Test");

			// 15.part //24.TextSwitcher
			driver.findElement(AppiumBy.accessibilityId("TextSwitcher")).click();
			test.pass("Successfully navigated to 'TextSwitcher'.");

			driver.findElement(AppiumBy.accessibilityId("Next")).click();
			test.pass("Clicked on 'Next' button once.");

			driver.findElement(AppiumBy.accessibilityId("Next")).click();
			test.pass("Clicked on 'Next' button again.");

			driver.navigate().back();
			test.pass("Navigated back from 'TextSwitcher' section.");

		} catch (Exception e) {
			test.fail("Test TextSwitcher failed: " + e.getMessage().split("\n")[0]);
		}
	}

	@Test
	public void testTextVisibilitySection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Visibility Lists Section Test");

			// Scroll to the "Visibility" option
			driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
					+ ".scrollIntoView(new UiSelector().text(\"Visibility\"));"));
			test.pass("Scrolled to 'Visibility' option.");

			// 16.part //25.Visibility
			// Click on "Visibility"
			driver.findElement(AppiumBy.accessibilityId("Visibility")).click();
			test.pass("Successfully navigated to 'Visibility'.");

			driver.findElement(AppiumBy.accessibilityId("Invis")).click();
			test.pass("Clicked on 'Invis'.");

			driver.findElement(AppiumBy.accessibilityId("Gone")).click();
			test.pass("Clicked on 'Gone'.");

			driver.findElement(AppiumBy.accessibilityId("Vis")).click();
			test.pass("Clicked on 'Vis'.");

			driver.navigate().back();
			test.pass("Navigated back from 'Visibility' section.");

			Thread.sleep(2000);
			System.out.println("Session closed");
			driver.quit();

		} catch (Exception e) {
			test.fail("Test Visibility failed: " + e.getMessage().split("\n")[0]);
		}

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}

}
