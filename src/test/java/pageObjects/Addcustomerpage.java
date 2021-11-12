package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Addcustomerpage {
	public WebDriver ldriver;
	
	public Addcustomerpage(WebDriver rdriver) 
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver,this);
	}
	
	By lnkCustomers_menu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/a/p");
	By lnkCustomers_menuitem=By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	
	By btnAddnew=By.xpath("//a[@class='btn btn-primary']"); //Add new
	
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
	
	By lstitemAdministrators=By.xpath("//li[normalize-space()='Administrators']");
    By lstitemRegistered=By.xpath("//li[@id='7425741b-ad84-4b16-aae7-266738362f60']");
	By lstitemGuests=By.xpath("//li[normalize-space()='Guests']");
    By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
    By lstitemForumModerators=By.xpath("//li[normalize-space()='Forum Moderators']");
	
    By drpmgrOfVendor=By.xpath("//select[@id='VendorId']");
    By rdMaleGender=By.id("Gender_Male");
    By rdFeMaleGender=By.id("Gender_Female");
    
    By txtFirstName=By.xpath("//input[@id='FirstName']");
    By txtLastName=By.xpath("//input[@id='LastName']");
    
    By txtDob=By.xpath("//input[@id='DateOfBirth']");
    
    By txtCompanyName=By.xpath("//input[@id='Company']");
    
    By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
    		
    By btnSave=By.xpath("//button[@name='save']");
    		

  //Action Methods
    
    public String getPageTitle() {
    	return ldriver.getTitle();
    }

    public void clickOnCustomerMenu() {
    	ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomerMenuItem() {
    	ldriver.findElement(lnkCustomers_menuitem).click();
    }
    
    public void clickOnAddNew() {
    	ldriver.findElement(btnAddnew).click();
    }
    
    public void setEmail(String email) {
    	ldriver.findElement(txtEmail).sendKeys(email);
    	
    }
    
    public void setPassword(String password) {
    	ldriver.findElement(txtPassword).sendKeys(password);
    	
    }
    
    public void setCustomerRoles(String role) throws InterruptedException 
    {
    	if (!role.equals("Vendors"))
    			{ //PROBLEM!!!!!!!!!!!!---->solved
    		      ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
    			} 
    	        ldriver.findElement(txtcustomerRoles).click();
    	        WebElement listitem;
    	        Thread.sleep(3000);
    	
    	        if(role.equals("Administrators"))
    	        {
    	        	listitem=ldriver.findElement(lstitemAdministrators);
    	        }
    	        else if(role.equals("Guest")) {
    	        	listitem=ldriver.findElement(lstitemGuests);
    	        }
    	        else if(role.equals("Registered"))
    	        {
    	        	listitem=ldriver.findElement(lstitemRegistered);
    	        }
    	        else if(role.equals("Vendors"))
    	        {
    	        	listitem=ldriver.findElement(lstitemVendors);
    	        }
    	        else 
    	        {
    	        	listitem=ldriver.findElement(lstitemGuests);
    	        }
    	        listitem.click();
    	//JavascriptExecutor js = (JavascriptExecutor)ldriver;
        //js.executeScript("argument[0].click();",listitem); 
        
    } 
    
    public void setManagerOfVendor(String value) {
    	Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
    	drp.selectByVisibleText(value);
    	
    }
    
    public void setGender(String gender)
    {
    	if(gender.equals("Male"))
    	{
    		ldriver.findElement(rdMaleGender).click();
    	}
    	else if(gender.equals("Female"))
    	{
    		ldriver.findElement(rdFeMaleGender).click();
    	}
    	else
    	{
    		ldriver.findElement(rdMaleGender).click();
    	}
    	
    }
    
    
    public void setFirstName(String fname)
    {
    	ldriver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname)
    {
    	ldriver.findElement(txtLastName).sendKeys(lname);
    } 

    public void setDob(String dob)
    {
    	ldriver.findElement(txtDob).sendKeys(dob);
    }

    public void setCompanyName(String comname)
    {
    	ldriver.findElement(txtCompanyName).sendKeys(comname);
    }

    public void setAdminContent(String content)
    {
    	ldriver.findElement(txtAdminContent).sendKeys(content);
    }

    public void clickOnSave()
    {
    	ldriver.findElement(btnSave).click();
    }
    
    
    
}
    







