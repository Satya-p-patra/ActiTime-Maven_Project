package com.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.actitime.utilities.BasePage;

public class Home extends BasePage {

	@FindBy(xpath = "//span[text()='Tasks']")
	private WebElement tasksLink;

	@FindBy(xpath = "//span[text()='List Management']")
	private WebElement listmanagementLink;

	public Home(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void clickOnTask() {
		waitForVisibility(tasksLink);
		highlightElement(tasksLink);
		click(tasksLink);
	}

	public void clickOnListManagement() {
		waitForVisibility(listmanagementLink);
		highlightElement(listmanagementLink);
		click(listmanagementLink);
	}
}
