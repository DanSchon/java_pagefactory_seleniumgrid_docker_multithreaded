package multithreading;

import java.net.MalformedURLException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthenticationTest extends TestBase {
	
	
	@Test(dataProvider="getBrowsers")
	public void userIsAbleToAuthenticateSuccessfullyJira001(String browser) throws MalformedURLException {
	/*
	 * ID: Jira_001
	 * Description: A registered user can login to the site successfully
	 * Steps:
	 * navigate to base url
	 * enter valid credentials
	 * click login
	 * Expected Outcome:
	 * user is redirected to home page
	 */
		openBrowser(browser);
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.login("tomsmith", "SuperSecretPassword!");
		HomePage homePage = new HomePage(getDriver());
		
		Assert.assertTrue(homePage.logoutButton.isDisplayed());
		
		quitBrowser();
	}
	
	
}
