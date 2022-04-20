package com.harman.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWrapper {
	protected WebDriver driver;

	@BeforeMethod
	public void setup() {

		String browserName = "ch";

		if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();

		} else {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get("https://opensource-demo.orangehrmlive.com/");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
