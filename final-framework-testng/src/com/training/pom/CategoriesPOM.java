package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoriesPOM {
	
	private WebDriver driver;

	public CategoriesPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@href='edit-tags.php?taxonomy=category']")
	private WebElement catg;
	public void clickOnCategory(){
		this.catg.click();
	}
	
	@FindBy(id="tag-name")
	private WebElement name;
	public void enterNmae(){
		this.name.sendKeys("New Launches");
		String str = name.getAttribute("value");
		System.out.println("Entered Name is:  "+str);
	}
	
	@FindBy(id="tag-slug")
	private WebElement slug;
	public void enterSlug(){
		this.slug.sendKeys("launch");
		String str = slug.getAttribute("value");
		System.out.println("Entered slug is:  "+str);
	}
	
	@FindBy(id="tag-description")
	private WebElement desc;
	public void enterDesc(){
		this.desc.sendKeys("New Launches of vilas, apartments, flats");
		String str = desc.getAttribute("value");
		System.out.println("Entered Description is:  "+str);
	}
	
	@FindBy(id="submit")
	private WebElement submit;
	public void submitCategory(){
		this.submit.click();
		System.out.println("New Category is added successfully ");
	}

}
