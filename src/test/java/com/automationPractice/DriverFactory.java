package com.automationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	private WebDriver driver;
	ReadPropertyFile readPropertyFile = new ReadPropertyFile();

	public DriverFactory() {
		WebDriverManager.chromedriver().version(readPropertyFile.getChromeVersion()).setup();
		driver = new ChromeDriver();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void quitDriver() {
		driver.quit();
	}
}
