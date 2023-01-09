package Com.internetbanking.BaseClass;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Com.internetbanking.Utilities.TestUtil;
import Com.internetbanking.configuration.ReadConfigiration;

@SuppressWarnings("unused")
    public class BaseClass {
	public static String screenshotsubfoldername;
	ReadConfigiration readconfig = new ReadConfigiration();

	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;


	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void initialization() {

		logger = Logger.getLogger("Banking");
		PropertyConfigurator.configure("Log4j.properties");
       System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
       // System.setProperty("webdriver.chrome.driver","D:\\TechMax Data(14-10-22)\\chromedriver_win32\\chromedriver.exe");
		
	     driver = new ChromeDriver();
	     
	     driver.manage().window().maximize();
	     
	     driver.manage().deleteAllCookies();
	     
	     driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	     
	     driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	     
	     driver.get(baseURL);
	}
 public  void getScreenshotAs(String testmethodname) throws IOException
	 {
	 if(screenshotsubfoldername==null)
	 {
		 LocalDateTime myDateObj = LocalDateTime.now();
		  DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
         screenshotsubfoldername = myDateObj.format(myFormatObj);
		   
	 }

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/"+screenshotsubfoldername+"/"+testmethodname);
		FileUtils.copyFile(sourceFile, destFile);
		System.out.println("Screenshot saved successfully");
	   }


	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}