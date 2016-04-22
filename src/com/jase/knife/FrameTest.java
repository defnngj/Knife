package com.jase.knife;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class FrameTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		File file = new File("D:/javase/jase02/webdriver_api/iframe.html");
		
		String filePath = file.getAbsolutePath();
		driver.get(filePath);
		WebElement xpath = driver.findElement(By.xpath("//*[@id='if']"));
		// ÇÐ»»µ½ iframe£¨id = "if"£©  id ,name
		driver.switchTo().frame(xpath);
		driver.findElement(By.id("kw")).sendKeys("webdriver");
		driver.findElement(By.id("su")).click();
		Thread.sleep(5000);
		driver.quit();
	}
}
