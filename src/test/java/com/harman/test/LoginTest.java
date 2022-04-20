package com.harman.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.harman.base.WebDriverWrapper;

public class LoginTest extends WebDriverWrapper {

	@Test
	public void validCredentialTest() {

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();

		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
	}

	@DataProvider
	public String[][] invalidCredentialData() {
		String[][] main = new String[2][3];
		main[0][0] = "john";
		main[0][1] = "john123";
		main[0][2] = "Invalid credentials";

		main[1][0] = "peter";
		main[1][1] = "peter123";
		main[1][2] = "Invalid credentials";

		return main;
	}

	@Test(dataProvider = "invalidCredentialData")
	public void invalidCredentialTest(String username, String password, String expectedError) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();

		String actualError = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(actualError, expectedError);
	}

}
