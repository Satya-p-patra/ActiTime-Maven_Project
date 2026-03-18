package com.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.actitime.utilities.BasePage;

public class Profile extends BasePage {
	@FindBy(xpath = "(//div[text()='SP'])[1]")
	private WebElement profileButton;

	@FindBy(xpath = "//div[text()='Logout']")
	private WebElement logoutButton;

	public Profile(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void logout() {
		waitForVisibility(profileButton);
		highlightElement(profileButton);
		jsClick(profileButton);
		waitForVisibility(logoutButton);
		highlightElement(logoutButton);
		click(logoutButton, "Clicked on logout button.!");
	}

	public void validateLogout() {
		wait.until(ExpectedConditions.titleIs("actiTIME - Login"));
		Assert.assertEquals(driver.getTitle().equals("actiTIME - Login"), true, "Failed logging out the user..!");
		Reporter.log("User logged out..!", true);
	}
}