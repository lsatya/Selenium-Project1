package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BlogPOM {
	WebDriver driver;
	public BlogPOM(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@id='menu-item-617']//a[contains(text(),'Blog')]")
	private WebElement blogmenu;
	public void blogmenu(){
		this.blogmenu.click();
	}
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]/a[1]")
	private WebElement createdBy;
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]")
	private WebElement readMore;
	public void readMore() throws InterruptedException{
		String str = createdBy.getText();
		System.out.println("Text is: "+str);
		Assert.assertEquals(str, "manipal global");
		Thread.sleep(3000);
		this.readMore.click();
	}
	
	@FindBy(xpath="//textarea[@id='comment']")
	private WebElement textBox;
	public void commentBox(){
		this.textBox.sendKeys("good");
	}
	
	@FindBy(xpath="//input[@id='submit']")
	private WebElement postCmt;
	public void postComment(){
		this.postCmt.click();
	}
}
