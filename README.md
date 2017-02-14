# Knife
WebUI automation testing framework based on Selenium

本框架参考了Degger: https://github.com/NetEase/Dagger

介绍：
  Knife基于Selenium（WebDriver）进行了简单的二次封装，比Selenium所提供的原生方法操作更简单。Knife参考了Dagger，功能相对来说要简单很多。
  
特点：
* 简单封装有selenium原生的方法，使用更加简单。
* 同样支持有多种定位方式（id,name,class,linkText,xpath,css）。
* 结合TestNG单元测试框架，可以完整实现自动化用例的组织、运行和生成报告。
* 加入arrow之后，可以实现用例失败之后重试，重试次数也可自由配置；大大增加了用例的稳定性。
* 自带了丰富的例子，可以帮助你学习和使用Knife。

推荐使用：
* 集成开发工具：IntelliJ IDEA
* 项目管理：Maven

依赖框架与库：
* Selenium3 :http://www.seleniumhq.org/ (通过Maven更新)
* log4j :http://logging.apache.org/log4j/1.2/(通过Maven更新)
* testNG  :http://testng.org/doc/index.html(通过Maven更新)
* arrow  :https://github.com/NetEase/arrow/releases(需要单独下载，并导入IDE)


例子：
   请查看com.knife.demo以及login.knife.demo目录

=====================================================
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

==========================================================

设置用运例运行失败重试次数：
* 打开项目根目录下的config.properties 文件，修改retrycount参数值。