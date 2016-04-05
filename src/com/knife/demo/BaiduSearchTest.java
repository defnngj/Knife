package com.knife.demo;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.jase.knife.BrowserEmulator;


public class BaiduSearchTest {
	BrowserEmulator be;
	String baseUrl;
	
	String searchBox = "//*[@id='kw']";
	String searchBtn = "//*[@id='su']";

	@BeforeClass
	public void setUp() throws Exception {
		baseUrl = "https://www.baidu.com";
		be = new BrowserEmulator();
	}

	@Test
	public void testCase() throws Exception {
	
		be.open(baseUrl);
		be.type(searchBox, "github");
		be.click(searchBtn);
		Thread.sleep(2000);
		assertEquals(be.getTitle(), "github_°Ù¶ÈËÑË÷");
	}

	@AfterClass
	public void tearDown() throws Exception {
		be.quit();
	}
}