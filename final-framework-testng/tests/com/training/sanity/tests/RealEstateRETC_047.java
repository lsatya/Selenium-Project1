package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.BlogPOM;
import com.training.pom.CommentsPOM;
import com.training.pom.PropertiesPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class RealEstateRETC_047 {
	WebDriver driver;
	private String baseUrl;
    private static Properties properties;
    private RealEstateLoginPOM loginPOM;
    private PropertiesPOM propPOM;
    
	 @BeforeClass
	 public static void setUpBeforeClassRETC_047() throws IOException {
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
  public void OpsOnproperties() throws InterruptedException {
	  
	  	loginPOM.sendUserName("manipalglobal");
		loginPOM.sendPassword("manipalglobal@123");
		loginPOM.clickLoginBtn();
		propPOM.clickOnProperty();
		propPOM.clickonaddNewProperty();
		propPOM.enterPropTitle();
		propPOM.enterBodyText();
		propPOM.checkFeature();
		propPOM.selectRegion();
		propPOM.moveToTrash();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		propPOM.trashMessage();
		propPOM.VerifyTrash();
  }
 

  @AfterMethod
  public void tearDown() throws InterruptedException {
	  Thread.sleep(1000);
		driver.quit();
  }

 

}
