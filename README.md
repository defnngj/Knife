# Knife
WebUI automation testing framework based on Selenium

本框架参考了Degger:https://github.com/NetEase/Dagger

介绍：
  Knife基于selenium（webdriver）进行了简单的二次封装，比selenium所提供的原生方法操作更简单。Knife参考了Dagger，功能相对来说要简陋很多。
  
特点：
* 简单封装有selenium原生的方法，使用更加简单。
* 同样支持有多种定位方式（id,name,class,linkText,xpath,css）。
* 结合TestNG单元测试框架，可以完整实现自动化用例的组织、运行和生成报告。
* 加入arrow之后，可以实用例失败之后重新执行，重复次数可配置；大大增加了用例的稳定性。

依赖框架与库：
* selenium :http://www.seleniumhq.org/ (selenium-server-standalone-2.53.0.jar)
* log4j :http://logging.apache.org/log4j/1.2/
* testNG  :http://testng.org/doc/index.html
* arrow  :https://github.com/NetEase/arrow/releases


例子：
   请查看com.knife.demo目录

=====================================================

    package com.knife.demo;

    import com.jase.knife.BrowserEmulator;

    /**
     * The demo to show Knife basic usage
     * @author bugmaster
     */
    public class BaiduSearch {

        public static void main(String[] args) throws InterruptedException {

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
==========================================================