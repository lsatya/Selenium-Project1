package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostOptionsPOM {
	private WebDriver driver; 
	String count1;
	String count2;
	String count3;
	
	public PostOptionsPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Edit")
	private WebElement edit;
	
	@FindBy(linkText="Quick Edit")
	private WebElement quickEdit;
	
	@FindBy(linkText="Trash")
	private WebElement trash;
	
	
	
	@FindBy(linkText="View")
	private WebElement preview;
	
	//@FindBy(xpath="//a[contains(text(),'Undo')]")
	@FindBy(linkText="Undo")
	private WebElement undo;
	
	public void undodelete() throws InterruptedException{
		Thread.sleep(5000);
		this.undo.click();
	}
	
	@FindBy(xpath="//span[@class='displaying-num']")
	private WebElement noOfPosts;
	
	public void noOfPostsBeforeUndo(){
		count1 = noOfPosts.getText();
		System.out.println("No of Posts before Undo: "+count1);
	}
	public void noOfPostsAfterTrash(){
		count2 = noOfPosts.getText();
		System.out.println("No of Posts after Trash: "+count2);
	}
	
	public void noOfPostsAfterUndo(){
		count3 = noOfPosts.getText();
		System.out.println("No of Posts after Undo: "+count3);
	}
	
	public void verifyNoOfPosts(){
		Assert.assertEquals(count1, count3, "Undo is not working properly");
		System.out.println("Undo functionality is working fine");
	}
		
	@FindBy(xpath="//*/tr[1]/td[1]/strong[1]/a[1]")
	private WebElement post1;
	
	public void verifyPostLinks(){
		Actions act = new Actions(driver);
		act.moveToElement(post1).build().perform();
		String str1 = edit.getText();
		Assert.assertEquals(str1,"Edit","Edit link is not under Posts" );
		String str2 = quickEdit.getText();
		Assert.assertEquals(str2,"Quick Edit","Quick Edit link is not under Posts");
		String str3 = trash.getText();
		Assert.assertEquals(str3,"Trash","Trash link is not under Posts");
		String str4 = preview.getText();
		Assert.assertEquals(str4,"View","View link is not under Posts");
		System.out.println("All Links under Posts are present");
	}
	public void clickOnTrash(){
		this.trash.click();
	}
	
}
