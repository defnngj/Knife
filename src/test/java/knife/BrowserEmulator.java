/*
 * Copyright (c) 2016-2017 Knife, Inc. and other contributors
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
package knife;
import java.util.Set;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * knife.BrowserEmulator is based on Selenium3 and adds some enhancements
 *
 * @author bugmaster
 */
public class BrowserEmulator {

    static WebDriver browser;
    ChromeDriverService chromeServer;
    JavascriptExecutor javaScriptExecutor;

    int timeout = Integer.parseInt(GlobalSettings.timeout);

    public BrowserEmulator() {

        int browserType = GlobalSettings.browserType;

        if (browserType == 1) {
            browser = new FirefoxDriver();
        } else if (browserType == 2) {
            browser = new ChromeDriver();
        } else if (browserType == 3) {
            browser = new InternetExplorerDriver();
        } else if (browserType == 4) {
            browser = new EdgeDriver();
        } else if (browserType == 5) {
            browser = new OperaDriver();
        } else if (browserType == 6) {
            browser = new PhantomJSDriver();
        } else {
            Assert.fail("Not found browser, See the 'prop.properties' file, configure the browser type.");
        }

    }

    /**
     * Analyzing targeting elements, and positioning elements
     *
     * @param xpath
     *            the element's
     */
    public WebElement getElement(String xpath) {

        if (xpath.contains("=>") == false) {
            Assert.fail("Positioning syntax errors, lack of '=>'.");
        }

        String by = xpath.split("=>")[0];
        String value = xpath.split("=>")[1];

        if (by.equals("id")) {
            WebElement element = browser.findElement(By.id(value));
            return element;
        } else if (by.equals("name")) {
            WebElement element = browser.findElement(By.name(value));
            return element;
        } else if (by.equals("class")) {
            WebElement element = browser.findElement(By.className(value));
            return element;
        } else if (by.equals("linkText")) {
            WebElement element = browser.findElement(By.linkText(value));
            return element;
        } else if (by.equals("xpath")) {
            WebElement element = browser.findElement(By.xpath(value));
            return element;
        } else if (by.equals("css")) {
            WebElement element = browser.findElement(By.cssSelector(value));
            return element;
        } else {
            Assert.fail("Please enter the correct targeting elements,'id','name','class','xpaht','css'.");
        }
        return null;

    }

    /**
     * Wait for an element within a certain amount of time
     *
     * @param xpath
     *            the element's xpath the second
     */
    public void waitElement(String xpath, int second) {

        if (xpath.contains("=>") == false) {
            Assert.fail("Positioning syntax errors, lack of '=>'.");
        }

        String by = xpath.split("=>")[0];
        String value = xpath.split("=>")[1];
        By findelement = null;

        if (by.equals("id")) {
            findelement = By.id(value);
        } else if (by.equals("name")) {
            findelement = By.name(value);
        } else if (by.equals("class")) {
            findelement = By.className(value);
        } else if (by.equals("linkText")) {
            findelement = By.linkText(value);
        } else if (by.equals("xpath")) {
            findelement = By.xpath(value);
        } else if (by.equals("css")) {
            findelement = By.cssSelector(value);
        } else {
            Assert.fail("Please enter the correct targeting elements,'id','name','class','xpaht','css'.");

        }
        new WebDriverWait(browser, second).until(ExpectedConditions
                .presenceOfElementLocated(findelement));

    }

    /**
     * Open the URL
     *
     * @param url
     */
    public void open(String url) {
        // pause(stepInterval);
        try {
            browser.get(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set browser window wide and high.
     *
     * @param wide
     * @param high
     */
    public void setWindow(int wide, int high) {

        browser.manage().window().setSize(new Dimension(wide, high));
    }

    /**
     * Setting browser window is maximized
     *
     */
    public void maxWindow() {

        browser.manage().window().maximize();
    }

    /**
     * close the browser Simulates the user clicking the "close" button in the
     * title bar of a pop up
     */
    public void close() {
        browser.close();
    }

    /**
     * Quit the browser
     */
    public void quit() {
        browser.quit();
    }

    /**
     * Click the page element
     *
     * @param xpath
     *            the element's xpath
     */
    public void click(String xpath) {

        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Type text at the page element<br>
     * Before typing, try to clear existed text
     *
     * @param xpath
     *            , the element's xpath
     * @param text
     *            , the input text
     */
    public void type(String xpath, String text) {

        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);

        try {
            element.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Right click element.
     *
     * @param xpath
     *            the element's xpath
     */
    public void rightClick(String xpath) {
        waitElement(xpath, timeout);

        Actions action = new Actions(browser);
        WebElement element = getElement(xpath);

        action.contextClick(element).perform();
    }

    /**
     * click and hold element.
     *
     * @param xpath
     *            the element's xpath
     */
    public void clickAndHold(String xpath) {
        waitElement(xpath, timeout);

        Actions action = new Actions(browser);
        WebElement element = getElement(xpath);

        action.clickAndHold(element).perform();
    }

    /**
     * Drags an element a certain distance and then drops it.
     *
     * @param el_xpath
     *            , the element's xpath
     * @param ta_xpath
     *            , the element's xpath
     */
    public void dragAndDrop(String el_xpath, String ta_xpath) {
        waitElement(el_xpath, timeout);
        waitElement(ta_xpath, timeout);

        Actions action = new Actions(browser);
        WebElement el = getElement(el_xpath);
        WebElement ta = getElement(ta_xpath);

        action.dragAndDrop(el, ta).perform();
    }

    /**
     * Click the element by the link text.
     *
     * @param text
     *            , the element's link text
     */
    public void clickText(String text) {

        WebElement element = browser.findElement(By.partialLinkText(text));
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select the select tag value
     *
     * @param xpath
     * @param value
     */
    public void selectValue(String xpath, String value) {

        waitElement(xpath, timeout);

        WebElement element = getElement(xpath);
        Select sel = new Select(element);
        sel.selectByValue(value);
    }

    /**
     * Refresh the browser
     */
    public void refresh() {
        browser.navigate().refresh();
    }

    /**
     * Execute JavaScript scripts.
     */
    public void js(String js) {
        ((JavascriptExecutor) browser).executeScript(js);
    }

    /**
     * Enter the iframe
     *
     * @param xpath
     *            , the iframe's xpath
     */
    public void enterFrame(String xpath) {
        waitElement(xpath, timeout);
        WebElement element = getElement(xpath);
        browser.switchTo().frame(element);
    }

    /**
     * Leave the iframe
     */
    public void leaveFrame() {
        browser.switchTo().defaultContent();
    }

    /**
     * Open the new window and switch the handle to the newly opened window.
     *
     * @param xpath
     *            , the open windows element xpath
     */
    public void openOneWindow(String xpath) {

        waitElement(xpath, timeout);

        String sreach_handle = browser.getWindowHandle();
        WebElement element = getElement(xpath);
        try {
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<String> handles = browser.getWindowHandles();
        for (String handle : handles) {
            if (handle.equals(sreach_handle) == false) {
                browser.switchTo().window(handle);
            }
        }

    }

    /**
     * Return text from specified web element.
     *
     * @param xpath
     * @return
     */
    public String getText(String xpath) {
        WebElement element = getElement(xpath);
        return element.getText();
    }

    /**
     * Returns the title of the current page
     *
     * @return
     */
    public String getTitle() {
        return browser.getTitle();
    }

    /**
     * Returns the URL of the current page
     *
     * @return
     */
    public String getUrl() {
        return browser.getCurrentUrl();
    }

    /**
     * Gets the value of an element attribute.
     *
     * @return
     */
    public String getAttribute(String xpath, String attribute) {
        WebElement element = getElement(xpath);
        String value = element.getAttribute(attribute);
        return value;
    }

    /**
     * Accept warning box.
     */
    public void acceptAlert() {
        browser.switchTo().alert().accept();
    }

    /**
     * Dismisses the alert available.
     */
    public void dismissAlert() {
        browser.switchTo().alert().dismiss();
    }

    /**
     * TakesScreenshot.
     */
    public void TakesScreenshot(String file_path) {
        try {
            File srcFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile,new File(file_path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
