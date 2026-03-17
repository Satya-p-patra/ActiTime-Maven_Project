package com.actitime.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.actitime.utilities.BasePage;
import com.actitime.utilities.ExcelLibrary;

public class NewCustomer extends BasePage {
	public WebDriver driver;

	@FindBy(xpath = "(//input[@placeholder='Enter Customer Name'])[2]")
	private WebElement entercustomernameTextbox;

	@FindBy(xpath = "//div[text()='Create Customer']")
	private WebElement createcustomerButton;

	public NewCustomer(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void createCustomer() throws InterruptedException, IOException {
		waitForVisibility(entercustomernameTextbox);

		entercustomernameTextbox.sendKeys(ExcelLibrary.getStringValueFromCell("Customer Page", 1, 0));
		waitForClickable(createcustomerButton);
		jsClick(createcustomerButton);
		Thread.sleep(5000);
	}
}