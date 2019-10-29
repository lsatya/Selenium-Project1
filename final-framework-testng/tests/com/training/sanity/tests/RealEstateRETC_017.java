package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.RealEstateLoginPOM;
import com.training.pom.MainPostPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class RealEstateRETC_017 {
	private WebDriver driver;
    private String baseUrl;
    private static Properties properties;
    private MainPostPOM mainPostPOM;
    private RealEstateLoginPOM loginPOM;
	
    @BeforeClass
    public static void setUpBeforeClass1() throws IOException {
        properties = new Properties();
        FileInputStream inStream = new FileInputStream("./resources/others.properties");
        properties.load(inStream);
    }
    @BeforeMethod
    public void beforeMethod() {
    	driver = DriverFactory.getDriver(DriverNames.CHROME);
		mainPostPOM = new MainPostPOM(driver);
		loginPOM = new RealEstateLoginPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
    }
  @Test
  public void addNewPost() {
	  loginPOM.sendUserName("manipalglobal");
	  loginPOM.sendPassword("manipalglobal@123");
	  loginPOM.clickLoginBtn(); 
	  mainPostPOM.clickPost();
	  mainPostPOM.addNewPost();
	  mainPostPOM.addPostTitle();
	  mainPostPOM.addPostBody();
	  mainPostPOM.publishPost();
	  mainPostPOM.viewPostMessage();	  
  }
  
   @AfterMethod
 	public void tearDown() throws Exception {
 		Thread.sleep(1000);
 		driver.quit();
   }
}
