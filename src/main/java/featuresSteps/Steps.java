package featuresSteps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.cucumber.listener.Reporter;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilityFunctions.InitializeDriver;
import utilityFunctions.ScreenShotCapture;


public class Steps {
	InitializeDriver bd=new InitializeDriver();
	WebDriver driver=bd.getDriver();

	@Given("^User have Application url to launch$")
	public void i_have_Application_url_to_launch() throws Throwable {
	 driver.get("https://ia.ca/");
	
	}

	@When("^User enters Outstanding Balance as \"([^\"]*)\"$")
	public void user_enters_Outstanding_Balance_as(String outStandingBalance) throws Throwable {
	    driver.findElement(By.name("txtOutstandingBal")).sendKeys(outStandingBalance);
	}

	@When("^Remaining term as \"([^\"]*)\" years \"([^\"]*)\" months$")
	public void remaining_term_as_years_months(String Year, String Month) throws Throwable {
		WebElement elementyear=driver.findElement(By.name("ddlRemainingTermYear"));
		Select selectYear=new Select(elementyear);
		selectYear.selectByVisibleText(Year);
		WebElement elementmonth=driver.findElement(By.name("ddlRemainingTermMonth"));
		Select selectMonth=new Select(elementmonth);
		selectMonth.selectByVisibleText(Month);
	}

	@When("^Payment frequency as \"([^\"]*)\"$")
	public void payment_frequency_as(String frequency){
		WebElement element=driver.findElement(By.name("ddlPayFrequency"));
		Select select=new Select(element);
		select.selectByVisibleText(frequency);
	}

	@When("^Payment amount as \"([^\"]*)\"$")
	public void payment_amount_as(String payAmount) {
		 driver.findElement(By.name("txtPayAmount")).sendKeys(payAmount);
	}

	@When("^Current Interest Rate as \"([^\"]*)\"$")
	public void current_Interest_Rate_as(String currentRate) throws Throwable {
		 driver.findElement(By.name("txtCurIntRate")).sendKeys(currentRate);
	}
	
	@When("^click on Calculate Button$")
	public void click_on_Calculate_Button() throws Throwable {
	   driver.findElement(By.name("btnCalculate")).click();
	}

	@Then("^\"([^\"]*)\" is displayed$")
	public void is_displayed(String errorMessage) throws Throwable {
		if(driver.findElement(By.id("rfvCurIntRate")).isDisplayed()){
			  String actualErrorMessage=driver.findElement(By.id("rfvCurIntRate")).getText();
			   Assert.assertEquals(errorMessage, actualErrorMessage);
			   Thread.sleep(2000);
			   String screenshotpath=ScreenShotCapture.takeScreenShot("ErrorMessage");
			   Reporter.addScreenCaptureFromPath(screenshotpath);
		}
	 
	}
	
	@Then("^(\\d+) Month Penalty is \"([^\"]*)\" displayed$")
	public void month_Penalty_is_displayed(int arg1, String expectedPenality) throws Throwable {
		WebElement element=driver.findElement(By.id("lblThreeMonthPenaltyResult"));
		if(element.isDisplayed()){
			 Actions actions=new Actions(driver);
			 actions.moveToElement(element).perform();
			 String penality=driver.findElement(By.id("lblThreeMonthPenaltyResult")).getText();
			 Assert.assertEquals(expectedPenality, penality);
			 Thread.sleep(2000);
			 String screenshotpath= ScreenShotCapture.takeScreenShot("Result");
			 Reporter.addScreenCaptureFromPath(screenshotpath);
		}
	}

	@Then("^Interest Rate Differential is \"([^\"]*)\" displayed$")
	public void interest_Rate_Differential_is_displayed(String differentialResult) throws Throwable {
		 WebElement element=driver.findElement(By.id("lblIRDResult"));
	 if(element.isDisplayed()){
		 Actions actions=new Actions(driver);
		 actions.moveToElement(element).perform();
		 String actualDifferential=driver.findElement(By.id("lblIRDResult")).getText();
		 Assert.assertEquals(differentialResult, actualDifferential);
	 }
	}

	@After
	public void closeBroswer(){
		driver.close();
	}

}
