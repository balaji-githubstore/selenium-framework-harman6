package com.harman.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.harman.base.WebDriverWrapper;
import com.harman.utilities.DataUtils;

public class LoginTest extends WebDriverWrapper {

	@Test
	public void validCredentialTest() {

		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//wait for dashboard page load
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='My Info']")));

		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
	}

	@Test(dataProviderClass = DataUtils.class,dataProvider = "invalidCredentialData")
	public void invalidCredentialTest(String username, String password, String expectedError) {
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		driver.findElement(By.id("btnLogin")).click();

		String actualError = driver.findElement(By.id("spanMessage")).getText();
		Assert.assertEquals(actualError, expectedError);
	}

}
