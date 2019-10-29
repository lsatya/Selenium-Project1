package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPostPOM {
	private WebDriver driver; 
	String str;
	
	public MainPostPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Posts')]")
	private WebElement mainpost;
	
	public void clickPost(){
		this.mainpost.click();
	}
	
	@FindBy(xpath="//a[@class='wp-first-item current']")
	private WebElement allPosts;
		
	@FindBy(xpath="//a[@href='post-new.php']")
	private WebElement addNew;
	
	
	@FindBy(xpath="//a[@href='edit-tags.php?taxonomy=category']")
	private WebElement catg;
	
	@FindBy(xpath="//a[@href='edit-tags.php?taxonomy=post_tag']")
	private WebElement tags;
	
	public void verifyPostMenu(){
		String str1 = allPosts.getText();
		Assert.assertEquals(str1,"All Posts","All Posts link is not under Posts" );
		String str2 = addNew.getText();
		Assert.assertEquals(str2, "Add New", "Add New link is not under Posts");
		String str3 = catg.getText();
		Assert.assertEquals(str3, "Categories", "Catagory link is not under Posts");
		String str4 = tags.getText();
		Assert.assertEquals(str4, "Tags", "Tags link is not under Posts");
		System.out.println("All Links under Posts are present");	
	}
	public void addNewPost(){
		this.addNew.click();
	}
	
	@FindBy(name="post_title")
	private WebElement titleText;
	public void addPostTitle(){
		this.titleText.clear();
		this.titleText.sendKeys("New Launches");
		String txt = titleText.getAttribute("value");
		System.out.println("Entered text in the Title is: "+txt);
	}
	@FindBy(id="tinymce")
	private WebElement bodyText;
	
	@FindBy(id="content-tmce")
	private WebElement visual;
	public void addPostBody(){
		this.visual.click();
		driver.switchTo().frame("content_ifr"); //content_ifr
		this.bodyText.sendKeys("New Launch in Home");
		str = bodyText.getAttribute("value");
		System.out.println("Entered text in the Body is: "+str);
		driver.switchTo().defaultContent();
	}
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[2]/div[2]/div[1]/div[3]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[2]")
	private WebElement publish;
	public void publishPost(){
		this.publish.click();
	}
	//@FindBy(xpath="//a[contains(text(),'View post')]")
	@FindBy(xpath="//p[contains(text(),'Post published.')]")
	private WebElement postmessage;
	public void viewPostMessage(){
		String viewText = this.postmessage.getText();
		Assert.assertEquals(viewText,"Post published. View post","Post is not published properly");
		System.out.println(viewText+"  is successfully published");
	}
	
}
