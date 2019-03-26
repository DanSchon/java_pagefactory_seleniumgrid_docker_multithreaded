package multithreading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	private RemoteWebDriver driver;
	
	@FindBy(id="username")
	public WebElement usernameField;
	
	@FindBy(id="password")
	public WebElement passwordField;
	
	@FindBy(css="#login > button")
	public WebElement loginButton;
	
	public LoginPage(RemoteWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
		driver.get("https://the-internet.herokuapp.com/login");
	}
	
	public void login(String username, String password) {
		this.usernameField.sendKeys(username);
		this.passwordField.sendKeys(password);
		this.loginButton.click();
	}

}
