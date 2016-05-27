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

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import com.jase.knife.BrowserEmulator;

/**
 * The demo to show Knife basic usage
 * @author bugmaster
 */
public class BaiduSearch {

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException, MalformedURLException {

		String baseUrl = "https://www.baidu.com";
		String searchBox = "id=>kw";
		String searchBtn = "xpath=>//*[@id='su']";
		
		BrowserEmulator driver = new BrowserEmulator();

		driver.open(baseUrl);
		driver.type(searchBox, "github");
		driver.click(searchBtn);
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.quit();
	}
}
