package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.pom.PropertiesPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RealEstateRETC_048 {
	WebDriver driver;
	private String baseUrl;
    private static Properties properties;
    private RealEstateLoginPOM loginPOM;
    private PropertiesPOM propPOM;
    
	 @BeforeClass
	 public static void setUpBeforeClassRETC_048() throws IOException {
	        properties = new Properties();
	        FileInputStream inStream = new FileInputStream("./resources/others.properties");
	        properties.load(inStream);
	    	  }
	 @BeforeMethod
	 public void setUp() {
			driver = DriverFactory.getDriver(DriverNames.CHROME);
			loginPOM = new RealEstateLoginPOM(driver); 
			propPOM = new PropertiesPOM(driver);
			baseUrl = properties.getProperty("baseURL");
			driver.get(baseUrl);
		  }
	
  @Test
  public void restorePost() throws InterruptedException {
	  
	  	loginPOM.sendUserName("manipalglobal");
		loginPOM.sendPassword("manipalglobal@123");
		loginPOM.clickLoginBtn();
		propPOM.clickOnProperty();
		propPOM.clickOnAllProps();
		propPOM.clickOnTrash();
		propPOM.clickOnRestore();
		propPOM.verifyResore();
  }
 

  @AfterMethod
  public void tearDown() throws InterruptedException {
	  Thread.sleep(1000);
		driver.quit();
  }
}
