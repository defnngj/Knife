package demo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import knife.BrowserEmulator;
/**
 * The demo to show Knife and TestNG basic usage
 * @author bugmaster
 */
public class BaiduDemo {
    BrowserEmulator driver;
    String baseUrl;

    String searchBox = "xpath=>//*[@id='kw']";
    String searchBtn = "xpath=>//*[@id='su']";

    @BeforeClass
    public void setUp() {
        baseUrl = "https://www.baidu.com";
        driver = new BrowserEmulator();
    }
    // 定义对象数组
    @DataProvider(name = "search")
    public Object[][] Keys() {
        return new Object[][] {
                {"Java"},{"testNG"},{"arrow"},{"Knife"}, };
    }

    @Test(dataProvider = "search")
    public void testSearch(String searchKey) throws InterruptedException {
        driver.open(baseUrl);
        driver.type(searchBox, searchKey);
        driver.click(searchBtn);
        Thread.sleep(2000);
        assertEquals(driver.getTitle(), searchKey+"_百度搜索");
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
