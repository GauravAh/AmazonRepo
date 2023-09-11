package com.Amazon.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	@BeforeClass
	public void browserSetUp() {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver.set(new ChromeDriver(options));

	}

	public static WebDriver getBrowser() {
		return driver.get();
	}
	

}
