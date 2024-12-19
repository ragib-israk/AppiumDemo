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

public class Content {

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
	public void testContentSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Content Test");
			test.info("Navigating to Content");
	           
         // Navigate to the Content section
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Content\"]")).click();
            test.pass("Successfully navigated to 'Content'.");
			test.info("Content is loaded.");
            
            
		} catch (Exception e) {
			test.fail("Test Content section failed: " + e.getMessage().split("\n")[0]);
		}
	}
            
            
	@Test
	public void testAssetsSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Assets Test");
			test.info("Navigating to Assets");

            // 1. Assets
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Assets\"]")).click();
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Read Asset\"]")).click();
            Thread.sleep(2000);  // Observe the UI
            driver.navigate().back();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Assets'.");
			test.info("Assets is loaded.");
			
			test.pass("Successfully navigated to 'Read Asset'.");
			test.info("Read Asset is loaded.");
            
		} catch (Exception e) {
			test.fail("Test Assets section failed: " + e.getMessage().split("\n")[0]);
		}
	}
            
	
	@Test
	public void testClipboardSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Clipboard Test");
			test.info("Navigating to Clipboard");


            // 2. Clipboard
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Clipboard\"]")).click();
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Data Types\"]")).click();
            driver.findElement(AppiumBy.id("io.appium.android.apis:id/copy_styled_text")).click();
            driver.navigate().back();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Clipboard'.");
			test.info("Clipboard is loaded.");
            
			test.pass("Successfully navigated to 'Data Types'.");
			test.info("Data Types is loaded.");
 
		} catch (Exception e) {
			test.fail("Test Clipboard section failed: " + e.getMessage().split("\n")[0]);		
			}
            
	}
	
	@Test
	public void testPackagesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Packages Test");
			test.info("Navigating to Packages");

            // 3. Packages
            driver.findElement(AppiumBy.accessibilityId("Packages")).click();
            driver.findElement(AppiumBy.accessibilityId("Install Apk")).click();
            driver.findElement(AppiumBy.accessibilityId("My Source")).click();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Packages'.");
			test.info("Packages is loaded.");
			
			test.pass("Successfully navigated to 'Install Apk'.");
			test.info("Install Apk is loaded.");
			
			test.pass("Successfully navigated to 'My Source'.");
			test.info("My Source is loaded.");
      
            
		} catch (Exception e) {
			test.fail("Test Packages section failed: " + e.getMessage().split("\n")[0]);
		}
	}
            
	
	@Test
	public void testProviderSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Provider Test");
			test.info("Navigating to Provider");

            // 4. Provider
            driver.findElement(AppiumBy.accessibilityId("Provider")).click();
            driver.findElement(AppiumBy.accessibilityId("Pick Contact")).click();
            driver.findElement(AppiumBy.accessibilityId("Pick a Person")).click();
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Provider'.");
			test.info("Provider is loaded.");
			
			test.pass("Successfully navigated to 'Pick Contact'.");
		    test.info("Pick Contact is loaded.");
		    
		    test.pass("Successfully navigated to 'Pick a Person'.");
		    test.info("Pick a Person is loaded.");
            
            
		} catch (Exception e) {
			test.fail("Test Provider section failed: " + e.getMessage().split("\n")[0]);
		}
	}
            

	
	@Test
	public void testResourcesSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Resources Test");
			test.info("Navigating to Resources");

            // 5. Resources
            driver.findElement(AppiumBy.accessibilityId("Resources")).click();
            driver.findElement(AppiumBy.accessibilityId("Layout Reference")).click();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Resources'.");
			test.info("Resources is loaded.");
			test.pass("Successfully navigated to 'Layout Reference'.");
			test.info("Layout Reference is loaded.");
            
            driver.findElement(AppiumBy.accessibilityId("Resources")).click();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Resources'.");
			test.info("Resources is loaded.");
			
			
            driver.findElement(AppiumBy.accessibilityId("Smallest Width")).click();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Smallest Width'.");
			test.info("Smallest Width is loaded.");
			
			
            driver.findElement(AppiumBy.accessibilityId("Styled Text")).click();
            driver.navigate().back();
            test.pass("Successfully navigated to 'Styled Text'.");
			test.info("Styled Text is loaded.");
			
			
            driver.findElement(AppiumBy.accessibilityId("Width and Height")).click();
            driver.navigate().back();
            driver.navigate().back();                        
            test.pass("Successfully navigated to 'Width and Height'.");
			test.info("Width and Height is loaded.");
            
		} catch (Exception e) {
			test.fail("Test Resources section failed: " + e.getMessage().split("\n")[0]);
		}         
	}       
            
	
	
	@Test
	public void testStorageSection() throws InterruptedException, IOException {

		try {
			test = extent.createTest("Storage Test");
			test.info("Navigating to Storage");


            // 6.Storage
            driver.findElement(AppiumBy.accessibilityId("Storage")).click();
            driver.findElement(AppiumBy.accessibilityId("External Storage")).click();
            test.pass("Successfully navigated to 'Storage'.");
			test.info("Storage is loaded.");
			test.pass("Successfully navigated to 'External Storage'.");
			test.info("External Storage is loaded.");

            // Locate and click on the second 'Create' button
            driver.findElement(AppiumBy.xpath("(//android.widget.Button[@content-desc='Create'])[2]")).click();
            Thread.sleep(2000);  // Observe any UI changes
            
            test.pass("Successfully navigated to ''Create' button'.");
			test.info("'Create' button is clicked.");

            // Locate and click on the second 'Delete' button
            driver.findElement(AppiumBy.xpath("(//android.widget.Button[@content-desc='Delete'])[2]")).click();
            Thread.sleep(2000);  // Observe any UI changes

            // Navigate back to the main screen
            driver.navigate().back();
            driver.navigate().back();
            test.pass("Successfully navigated to ''Delete' button'.");
			test.info("'Delete' button is clicked.");
            
		} catch (Exception e) {
			test.fail("Test Storage section failed: " + e.getMessage().split("\n")[0]);
		}

		Thread.sleep(2000);
		System.out.println("Session closed");
		driver.quit();

		// Flush ExtentReports
		extent.flush();
		Desktop.getDesktop().browse(new File("Html_report").toURI());
	}
}
