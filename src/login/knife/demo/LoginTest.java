package login.knife.demo;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import login.knife.demo.LoginUser;

import com.jase.knife.BrowserEmulator;

public class LoginTest {

	private BrowserEmulator driver;
	private String baseUrl;

	@BeforeMethod
	public void setUp() throws Exception {
		driver = new BrowserEmulator();
		baseUrl = "http://www.126.com";
	}

	// 定义对象数组
	@DataProvider(name = "user")
	public Object[][] Users() {
		return new Object[][] { { "", "", "请先输入您的邮箱帐号" },
				{ "testing", " ", "帐号或密码错误" }, { " ", "123456", "请先输入您的邮箱帐号" },
				{ "error", "error", "帐号或密码错误" }, };
	}

	@Test(dataProvider = "user")
	public void testCase(String username, String password, String expectText)throws Exception {
		driver.open(baseUrl);

		// 调用登录模块
		LoginUser.login(driver, username, password);
		Thread.sleep(2000);
		
		String text = LoginUser.login_error_info(driver);
		
		System.out.println(text);
		System.out.println(expectText);
		Assert.assertEquals(text, expectText);

	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
