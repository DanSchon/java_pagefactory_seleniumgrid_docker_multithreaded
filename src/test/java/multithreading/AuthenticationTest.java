package multithreading;

import java.io.IOException;
import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import multithreading.pageObjects.HomePage;
import multithreading.pageObjects.LoginPage;

public class AuthenticationTest extends TestBase {
	
	
	@Test(dataProvider="getBrowsers")
	public void userIsAbleToAuthenticateSuccessfullyJira001(String browser) throws IOException {
		test = rep.startTest("userIsAbleToAuthenticateSuccessfullyJira001 - "+ browser);
		setExtentTest(test);
		getExtTest().log(LogStatus.INFO, "Description: A registered user can login to the site successfully");
		getExtTest().log(LogStatus.INFO, "Steps:");
		getExtTest().log(LogStatus.INFO, "navigate to base url");
		getExtTest().log(LogStatus.INFO, "enter valid credentials");
		getExtTest().log(LogStatus.INFO, "click login");
		getExtTest().log(LogStatus.INFO, "Expected Outcome:");
		getExtTest().log(LogStatus.INFO, "user is redirected to home page");
		
		try {
		openBrowser(browser);
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("tomsmith", "SuperSecretPassword!");
		HomePage homePage = new HomePage(getDriver());
		
		Assert.assertTrue(homePage.logoutButton.isDisplayed());
		testPassed("userIsAbleToAuthenticateSuccessfullyJira001 - "+ browser + "PASSED");
		} catch(Exception e) {
			testFailed(e);
		}
	}
	
	
}
