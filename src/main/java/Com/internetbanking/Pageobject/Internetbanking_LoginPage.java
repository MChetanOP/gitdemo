package Com.internetbanking.Pageobject;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.internetbanking.baseclass.BaseClass;

public class Internetbanking_LoginPage extends BaseClass{

	
  //1.
  public Internetbanking_LoginPage() {
	  
	  PageFactory.initElements(driver,this);
  }
  
  //2.
  @FindBy(name = "uid")
  public WebElement Username;
  
  @FindBy(xpath = "//input[@name=\"password\"]")
  public WebElement Password;
  
  @FindBy(name = "btnLogin")
  public WebElement LoginBtn;
  
  @FindBy(xpath = "//a[contains(text(),'Log out')]")
  public WebElement Logout;
  
  
  //3.
  public void Setusername(String Uname) {
	  Username.sendKeys(Uname);
  }
  
  public void SetPassword(String Pwd) {
	  
	  Password.sendKeys(Pwd);
  }
  
  public void Clickbtn() {
	  LoginBtn.click();
	  
  }
  
  public void Logoutapplication() {
	  Logout.click();
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}