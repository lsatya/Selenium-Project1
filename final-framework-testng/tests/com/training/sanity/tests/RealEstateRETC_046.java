package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.pom.BlogPOM;
import com.training.pom.CategoriesPOM;
import com.training.pom.CommentsPOM;
import com.training.pom.MainPostPOM;
import com.training.pom.RealEstateLoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class RealEstateRETC_046 {
	private WebDriver driver;
    private String baseUrl;
    private static Properties properties;
    private RealEstateLoginPOM loginPOM;
    private BlogPOM blogPOM;
    private CommentsPOM commentPOM;
    
	
	@BeforeClass
	  		public static void setUpBeforeClassRETC_046() throws IOException {
	        properties = new Properties();
	        FileInputStream inStream = new FileInputStream("./resources/others.properties");
	        properties.load(inStream);
	    	  }
	
	@BeforeMethod
	  public void setUp() {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new RealEstateLoginPOM(driver); 
		blogPOM = new BlogPOM(driver);
		commentPOM = new CommentsPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		driver.get(baseUrl);
	  }
	
  //@Test
  public void enterIntoBlog() throws InterruptedException {
	  loginPOM.sendUserName("manipalglobal");
		loginPOM.sendPassword("manipalglobal@123");
		loginPOM.clickLoginBtn();
		loginPOM.homepage();
		blogPOM.blogmenu();
		blogPOM.readMore();
		blogPOM.commentBox();
		blogPOM.postComment();
  }
  
  @Test
  public void enterIntoComments() {
	  loginPOM.sendUserName("manipalglobal");
		loginPOM.sendPassword("manipalglobal@123");
		loginPOM.clickLoginBtn();
		commentPOM.commentLink();
		commentPOM.replyCountBefore();
		commentPOM.clickOnReply();
		commentPOM.enterReplyText();
		commentPOM.clickOnReplybtn();
		driver.navigate().refresh();
		commentPOM.replyCountAfter();
		commentPOM.verifyIncrement();
		
  }

  @AfterMethod
  public void tearDown() throws InterruptedException {
	  Thread.sleep(1000);
		driver.quit();
  }

  

}
