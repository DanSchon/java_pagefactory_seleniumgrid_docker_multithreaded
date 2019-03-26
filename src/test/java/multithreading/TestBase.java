package multithreading;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

public abstract class TestBase {

	private RemoteWebDriver driver;
	public static ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();
	
	public RemoteWebDriver getDriver() {
		return dr.get();
	}

	public void setDriver(RemoteWebDriver driver) {
		dr.set(driver);
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
	
	@DataProvider(parallel=true)
	public Object[][] getBrowsers() {
		Object[][] browsers = new Object[2][1];
		
		browsers[0][0] = "chrome";
		browsers[1][0] = "firefox";
		
		return browsers;
	}

}
