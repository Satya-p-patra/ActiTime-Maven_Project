package com.actitime.tests;

import java.io.IOException;
import org.testng.annotations.Test;

import com.actitime.pages.Home;
import com.actitime.pages.Login;
import com.actitime.pages.NewCustomer;
import com.actitime.pages.Tasks;
import com.actitime.utilities.BaseTest;

public class TC01_Create_Edit_Delete_Customer extends BaseTest {
	public Tasks tasks;

	@Test
	public void login() throws IOException {
		Login login = new Login(driver);
		login.login();
	}

	@Test(dependsOnMethods = "login")
	public void createCustomer() throws InterruptedException, IOException {
		Home home = new Home(driver);
		home.clickOnTask();

		Tasks tasks = new Tasks(driver);
		tasks.clickOnAddButton();
		tasks.clickOnNewCustomerButton();

		NewCustomer ns = new NewCustomer(driver);
		ns.createCustomer();

	}

	@Test(dependsOnMethods = "createCustomer")
	public void editCustomer() throws InterruptedException, IOException {
		tasks = new Tasks(driver);
		tasks.editcustomer();
		tasks.editcustomerValidate();
	}
}