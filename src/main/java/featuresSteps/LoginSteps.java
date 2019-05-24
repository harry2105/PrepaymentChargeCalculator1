package featuresSteps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilityFunctions.InitializeDriver;

public class LoginSteps {
	WebDriver driver;
	@Given("^I have application url and i am on login page$")
	public void i_have_application_url_and_i_am_on_login_page() throws Throwable {
	   InitializeDriver id=new InitializeDriver();
	  driver= id.getDriver();
	  driver.get("http://demo.testfire.net/login.jsp");
	}

	@When("^I enter username$")
	public void i_enter_username() throws Throwable {
	   driver.findElement(By.id("uid")).sendKeys("jsmith");
	}

	@When("^Enter password$")
	public void enter_password() throws Throwable {
		driver.findElement(By.id("passw")).sendKeys("Demo1234");
	}

	@When("^I click on login button$")
	public void i_click_on_login_button() throws Throwable {
	    driver.findElement(By.name("btnSubmit")).click();
	}

	@Then("^Home page is displayed$")
	public void home_page_is_displayed() throws Throwable {
	   boolean isDisplayed=driver.findElement(By.id("MenuHyperLink1")).isDisplayed();
	   Assert.assertTrue(isDisplayed);
	}

}
