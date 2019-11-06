package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.pom.MainPostPOM;
import com.training.pom.PostOptionsPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RealEstateRETC_078 {

	private WebDriver driver;
    private String baseUrl;
    private static Properties properties;
    private RealEstateLoginPOM loginPOM;
    private MainPostPOM mainPostPOM;
    private PostOptionsPOM postOptionsPOM;
   // private ScreenShot screenShot;
    
    @BeforeClass
    public static void setUpBeforeClass1() throws IOException {
        properties = new Properties();
        FileInputStream inStream = new FileInputStream("./resources/others.properties");
        properties.load(inStream);
    }
    @BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new RealEstateLoginPOM(driver); 
		mainPostPOM = new MainPostPOM(driver);
		postOptionsPOM = new PostOptionsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
	}
	
	
  @Test
  public void validLoginTest() throws InterruptedException {
		loginPOM.sendUserName("manipalglobal");
		loginPOM.sendPassword("manipalglobal@123");
		loginPOM.clickLoginBtn(); 
		mainPostPOM.clickPost();
		mainPostPOM.verifyPostMenu();
		postOptionsPOM.noOfPostsBeforeUndo();
		postOptionsPOM.verifyPostLinks();
		postOptionsPOM.clickOnTrash();
		postOptionsPOM.noOfPostsAfterTrash();
		postOptionsPOM.undodelete();
		postOptionsPOM.noOfPostsAfterUndo();
		postOptionsPOM.verifyNoOfPosts();
	}
  
 
  @AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit(); 
		}

	
}
