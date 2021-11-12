package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.Addcustomerpage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	
	
	@Before
    public void setup() throws IOException
    {

        //Logger steps
        logger= Logger.getLogger("FirstCucumberBddFramework");
        PropertyConfigurator.configure("Log4j.properties"); //added logger
        logger.setLevel(Level.DEBUG);
        //Logger steps done

        //Loading Config.properties file steps
        configProp=new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);
        //Loading Config.properties file is done


        String br=configProp.getProperty("browser");
       
        if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }

        else if (br.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }

         


    }
	
	

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		

		logger.info("********* Launching browser***************");

		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("********* Opening URL***************");

		driver.get(url);
		driver.manage().window().maximize();

	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("********* Proving user info ***************");

		lp.setUsername(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		lp.clickLogin();
		Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {

		logger.info("*********Login Validation starts ***************");

		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			logger.info("*********Login Failed ***************");

			driver.close();
			Assert.assertTrue(false);
		} else {
			logger.info("*********Login successfull ***************");

			Assert.assertEquals(title, driver.getTitle());
		}
		Thread.sleep(3000);

	}

	@When("User click on Logout link\"")
	public void user_click_on_logout_link() throws InterruptedException {
		logger.info("*********Logout from application***************");

		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		driver.quit();
	}

	// Customers features steps definition

	@Then("User can view Dashboad")
	public void user_can_view_dashboad() {
		logger.info("********* Adding Customer Scenario started  ***************");

		addCust = new Addcustomerpage(driver);
		logger.info("********* Dashboard Display validation ***************");

		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomerMenu();

	}

	@And("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomerMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddNew();
		Thread.sleep(2000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("********* Providing Customer details ***************");

		String email = randomestring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("John");
		addCust.setLastName("Smith");
		addCust.setDob("07/05/1985"); // format: dd/mm/yyyy.
		addCust.setCompanyName("Automation Engineer");
		addCust.setAdminContent("This is for Testing purpose");
		// Registered Default
		// The Customer cannot be in both 'Guest' and 'Registered customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guests");
		Thread.sleep(3000);

	}

	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCust.clickOnSave();
		Thread.sleep(2000);

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		logger.info("********* Add customer validation ***************");

		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	// Searching Customer using EmailID

	@And("Enter customer EMail")
	public void enter_customer_EMail() {
		logger.info("********* Search Customer by Email ID Scenario started ***************");

		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		logger.info("********* Search customer by email validation ***************");

		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}

	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		logger.info("********* Search customer by Name Scenario started ***************");

		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		logger.info("********* Providing customer name ***************");

		searchCust.setLastName("Terces");
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		logger.info("********* Search customer by name validation ***************");

		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	}

}
