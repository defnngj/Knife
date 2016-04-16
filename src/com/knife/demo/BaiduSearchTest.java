/*
 * Copyright (c) 2015-2016 Knife, Inc. and other contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.knife.demo;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.jase.knife.BrowserEmulator;
/**
 * The demo to show Knife and TestNG basic usage
 * @author bugmaster
 */

public class BaiduSearchTest {
	BrowserEmulator driver;
	String baseUrl;
	
	String searchBox = "xpath=>//*[@id='kw']";
	String searchBtn = "xpath=>//*[@id='su']";

	@BeforeClass
	public void setUp() throws Exception {
		baseUrl = "https://www.baidu.com";
		driver = new BrowserEmulator();
	}

	@Test
	public void testCase() throws Exception {
	
		driver.open(baseUrl);
		driver.type(searchBox, "github");
		driver.click(searchBtn);
		Thread.sleep(2000);
		assertEquals(driver.getTitle(), "github_°Ù¶ÈËÑË÷");
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.quit();
	}
}