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

import com.jase.knife.BrowserEmulator;

/**
 * The demo to show Knife basic usage
 * @author ChenKan
 */
public class BaiduSearch {

	public static void main(String[] args) {

		String baseUrl = "https://www.baidu.com";
		String searchBox = "//*[@id='kw']";
		String searchBtn = "//*[@id='su']";

		BrowserEmulator be = new BrowserEmulator();

		be.open(baseUrl);
		be.type(searchBox, "github");
		be.click(searchBtn);
		System.out.println(be.getTitle());
		be.quit();
		
	}
}
