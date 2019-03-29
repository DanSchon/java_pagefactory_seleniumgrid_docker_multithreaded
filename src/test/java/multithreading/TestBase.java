package multithreading;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import multithreading.utilities.ExtentManager;

public abstract class TestBase {

	private RemoteWebDriver driver;
	public ExtentTest test;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ThreadLocal<ExtentTest> exTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	public void captureScreenshot() throws IOException {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + screenshotName));
		String screenshotPath = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator + screenshotName;
		getExtTest().log(LogStatus.INFO,  " Screenshot -> "+ test.addScreenCapture(screenshotPath));
	}
	
	public void openBrowser(String browser) throws MalformedURLException {
        if (browser.equals("chrome")) { 
        	DesiredCapabilities dc = DesiredCapabilities.chrome();
        	driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        } else if (browser.equals("firefox")) {
        	DesiredCapabilities dc = DesiredCapabilities.firefox(); 
        	driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
        } else {
        	throw new IllegalArgumentException("Invalid argument: " + browser);
        } 
        setDriver(driver);
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void quitBrowser() {
		getDriver().quit();
	}
	
	public void testPassed(String message){
		getExtTest().log(LogStatus.PASS, message);
	}
	
	public void testFailed(Exception e) throws IOException{
		getExtTest().log(LogStatus.FAIL, e);
		captureScreenshot();
		quitBrowser();
		Assert.fail();
	}
	
	public void setExtentTest(ExtentTest et){
		exTest.set(et);
	}
		
	public ExtentTest getExtTest(){	
		return exTest.get();
	}
	
	public RemoteWebDriver getDriver() {
		return dr.get();
	}

	public void setDriver(RemoteWebDriver driver) {
		dr.set(driver);
	}
	
	@DataProvider(parallel=true)
	public Object[][] getBrowsers() {
		Object[][] browsers = new Object[2][1];
		
		browsers[0][0] = "chrome";
		browsers[1][0] = "firefox";
		
		return browsers;
	}
	
	@AfterMethod
	public void tearDown(){		
		if(rep!=null){
			rep.endTest(getExtTest());
			rep.flush();
		}
		quitBrowser();
	}

}
