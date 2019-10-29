package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PropertiesPOM {
	WebDriver driver;
	String titlestr, count1, count2;
	int cnt1,cnt2;
	public PropertiesPOM(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Properties')]")
	private WebElement propLink;
	public void clickOnProperty(){
		this.propLink.click();
	}
@FindBy(xpath="//li[@id='menu-posts-property']//ul[@class='wp-submenu wp-submenu-wrap']//li//a[contains(text(),'Add New')]")
private WebElement addProp;
public void clickonaddNewProperty(){
	this.addProp.click();
}
@FindBy(xpath=" //input[@name='post_title']")
private WebElement propTitle;
public void enterPropTitle() {
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.visibilityOf(propTitle));
	this.propTitle.clear();
	this.propTitle.sendKeys("Prestige");
	titlestr =  propTitle.getAttribute("value");
	System.out.println("Entered Property's Title is: "+titlestr);
}

@FindBy(xpath="//button[@id='content-tmce']")
private WebElement visualbtn;
@FindBy(xpath="//body[@id='tinymce']")
private WebElement body;
public void enterBodyText() throws InterruptedException{
	this.visualbtn.click();
	driver.switchTo().frame("content_ifr");
	this.body.sendKeys("home town");
	Thread.sleep(3000);
	driver.switchTo().defaultContent();
}

@FindBy(id="property_feature-all")
private WebElement allFeatures;
@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/ul[1]/li[2]/label[1]/input[1]")
private WebElement feature;
public void checkFeature(){
	this.allFeatures.click();
	this.feature.click();
	Assert.assertTrue(feature.isSelected(),"Feature Checkbox is not selected");
	System.out.println("Feature Checkbox is selected");
}

@FindBy(xpath="//a[contains(text(),'All Regions')]")
private WebElement allRegTab;
@FindBy(xpath="//input[@id='in-region-477']")
private WebElement region;
public void selectRegion(){
	this.allRegTab.click();
	this.region.click();
	Assert.assertTrue(region.isSelected(),"Region is not selected");
	System.out.println("The Region Manyata is selected");
}

@FindBy(xpath="//*/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/a[1]")
private WebElement trash;
public void moveToTrash(){
	/*WebDriverWait wait = new WebDriverWait(driver,5);
	wait.until(ExpectedConditions.visibilityOf(trash));*/
	Actions actions = new Actions(driver);
	actions.moveToElement(trash).click().build().perform();
	//this.trash.click();
}

@FindBy(xpath="//p[contains(text(),'1 post moved to the Trash.')]")
private WebElement trashmsg;
@FindBy(xpath="//a[contains(text(),'Undo')]")
private WebElement undo;
public void trashMessage(){
	String msg = trashmsg.getText();
	Assert.assertEquals(msg,"1 post moved to the Trash. Undo","Move to Trash operation is failed");
	System.out.println("1 post successfully moved to the Trash.");
	Assert.assertTrue(undo.isDisplayed(),"Undo message is not displayed");
	System.out.println("Undo link is displayed");
}
@FindBy(xpath="//li[@class='trash']//a[contains(text(),'Trash')]")
private WebElement trashbox;
@FindBy(xpath="//*/form[1]/table[1]/tbody[1]/tr[1]/td[1]/strong[1]")
private WebElement titleInTrash;
public void VerifyTrash(){
	this.trashbox.click();
	String title = titleInTrash.getText();
	System.out.println("Deleted Post Title is: "+title);
	Assert.assertEquals(title,titlestr,"Test is NOK");
	System.out.println("Test is OK");	
}


//Restore deleted Post

@FindBy(xpath="//a[@class='wp-first-item current']")
private WebElement allProps;
public void clickOnAllProps(){
	this.allProps.click();
}

@FindBy(xpath="//li[@class='trash']//a[contains(text(),'Trash')]")
private WebElement trash1;
@FindBy(xpath="//a[@class='current']//span[@class='count']")
private WebElement Trashcount;
public void clickOnTrash(){
	count1=Trashcount.getText();
	System.out.println("Trash Count Before Restore: "+count1);
	this.trash1.click();
	}

@FindBy(xpath="//*/form[1]/table[1]/tbody[1]/tr[1]/td[1]/strong[1]")
private WebElement deletedPost;
@FindBy(xpath="//*/form[1]/table[1]/tbody[1]/tr[1]/td[1]/div[2]/span[1]/a[1]")
private WebElement restore;
public void clickOnRestore(){
	Actions action = new Actions(driver);
	action.moveToElement(deletedPost).perform();
	this.restore.click();
}

@FindBy(xpath="//p[contains(text(),'1 post restored from the Trash.')]")
private WebElement restoreMsg;
public void verifyResore(){
	String restText = restoreMsg.getText();
	System.out.println("Resore Success message: "+restText);
	Assert.assertEquals(restText, "1 post restored from the Trash.","Restore is Un-Success");
	System.out.println("Restore is success");
	
	
	System.out.println("Trash Count Before Restore: "+count1);
	count2=Trashcount.getText();
	
	try{
		 cnt1 = Integer.parseInt(count1);
		 cnt2 = Integer.parseInt(count2);
		}
		catch (NumberFormatException ne){
			System.out.println("Int conversion exception");
		}
	
	System.out.println("Trash Count After Restore: "+cnt2);
		
	if(cnt1 == (cnt2-1)){
		System.out.println("Trash dicrementation is fine and Successfully Restored the deleted Post");
	} 
	else{
		System.out.println("Trash dicrementation is not working ");
	}
		
	System.out.println("Successfully Restored the deleted Post and Test is PASS");
}
}
