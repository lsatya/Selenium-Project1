package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CommentsPOM {
	WebDriver driver;
	String str1, str2;
	
	public CommentsPOM(WebDriver driver){
		 this.driver=driver;
			PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//div[contains(text(),'Comments')]")
	private WebElement comnt;
	public void commentLink(){
		this.comnt.click();
	}
	@FindBy(xpath="//tr[@id='comment-1959']//p[contains(text(),'good')]")
	private WebElement cmtText;
	@FindBy(xpath="//tr[@id='comment-1959']//a[@class='vim-r comment-inline'][contains(text(),'Reply')]")
	private WebElement reply;
	
	public void clickOnReply(){
		String str = cmtText.getText();
		System.out.println("Comment is: "+str);
		Assert.assertEquals(str, "good");
		Actions act = new Actions(driver);
		act.moveToElement(this.cmtText).perform();
		this.reply.click();
	}
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/table[1]/tbody[1]/tr[1]/td[3]/div[1]/span[1]/a[1]/span[1]")
	private WebElement count;

	public void replyCountBefore(){
		str1 = count.getText();
		System.out.println("Count before Reply is: "+str1);
	}
	@FindBy(xpath="//textarea[@id='replycontent']")
	private WebElement replyBody;
	public void enterReplyText(){
		this.replyBody.sendKeys("This is my Reply");
	}
	@FindBy(id="replybtn")
	private WebElement replybtn;
	public void clickOnReplybtn(){
		this.replybtn.click();
	}
	public void replyCountAfter(){
		str2 = count.getText();
		System.out.println("Count After Reply is: "+str2);
	}
	
	public void verifyIncrement(){
		int count1 = Integer.parseInt(str1);
		int count2 = Integer.parseInt(str2);
		if (count2==count1+1){
			System.out.println("Reply Count is incremented and the Test is PASS");
		} else {
			System.out.println("Reply Count is not incremented and the Test is FAIL");
		}
	}
	
}