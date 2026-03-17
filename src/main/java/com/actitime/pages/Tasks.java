package com.actitime.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;

import com.actitime.utilities.BasePage;
import com.actitime.utilities.ExcelLibrary;

public class Tasks extends BasePage {
	public WebDriver driver;

	// ============ Declaration ============
	@FindBy(xpath = "//div[@class='addNewButton']")
	private WebElement addButton;

	@FindBy(xpath = "//div[text()='New Customer']")
	private WebElement newcustomerLink;

	@FindBy(xpath = "//div[contains(@class,'node customerNode')]")
	private List<WebElement> allcustomerList;

	@FindBy(xpath = "//div[text()='qspiders']/../..//div[@class='editButton']")
	private WebElement customersettingButton;

	@FindBy(xpath = "(//div[text()='ACTIONS'])[1]")
	private WebElement actionsButton;

	@FindBy(xpath = "(//div[text()='Delete'])[2]")
	private WebElement deleteButton;

	@FindBy(xpath = "//span[text()='Delete permanently']")
	private WebElement confirmDeleteBtn;

	@FindBy(className = "nameLabel")
	private WebElement customereditField;

	@FindBy(xpath = "//input[@placeholder='Enter Customer Name']")
	private WebElement entercustomernameTextbox;

	// ============ Initialization ============
	public Tasks(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// ============ Utilization ============
	public void validateTaskPage() {
		wait.until(ExpectedConditions.titleIs("actiTIME - Task List"));
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, "actiTIME - Task List", "User hasn't navigated to Task page!");
	}

	public void clickOnAddButton() {
		waitForVisibility(addButton);
		highlightElement(addButton);
		click(addButton);
	}

	public void clickOnNewCustomerButton() {
		waitForVisibility(newcustomerLink);
		highlightElement(newcustomerLink);
		jsClick(newcustomerLink);
	}

	public void createcustomerValidate() throws InterruptedException {
		List<String> al = new ArrayList<String>();
		for (WebElement ele : allcustomerList) {
			al.add(ele.getText());
		}
		if (al.contains("qspiders"))
			System.out.println("customer is created");
		else
			System.out.println("customer is not created");
	}

	public void editcustomer() throws InterruptedException, IOException {
		waitForVisibility(customersettingButton);
		highlightElement(customersettingButton);
		customersettingButton.click();
		waitForClickable(customereditField);
		highlightElement(customereditField);
		click(customereditField);
		waitForVisibility(entercustomernameTextbox);
		clear(entercustomernameTextbox);
		entercustomernameTextbox.sendKeys(ExcelLibrary.getStringValueFromCell("Customer Page", 1, 1));
		entercustomernameTextbox.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}

	public void editcustomerValidate() {
		List<String> al = new ArrayList<String>();
		for (WebElement ele : allcustomerList) {
			al.add(ele.getText());
		}
		Assert.assertEquals(al.contains("test_Yantra"), true, "Something went wrong while editing the customerName..!");
		Reporter.log("Successfully edited the customerName..!", true);
	}

	public void deleteCustomer() throws InterruptedException {
		waitForVisibility(actionsButton);
		click(actionsButton);
		waitForVisibility(deleteButton);
		click(deleteButton);
		waitForVisibility(confirmDeleteBtn);
		click(confirmDeleteBtn);
		Thread.sleep(2000);
	}

	public void deletecustomerValidate() {
		List<String> al = new ArrayList<String>();
		for (WebElement ele : allcustomerList) {
			al.add(ele.getText());
		}
		if (!al.contains("test_Yantra"))
			System.out.println("Successfully deleted the customer..!");
		else
			System.out.println("Something went wrong while deleting the customer..!");
	}
}
