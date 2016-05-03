package login.knife.demo;

import com.jase.knife.BrowserEmulator;


public class LoginPage {
	
	BrowserEmulator driver;
	static String login_frame = "xpath=>//div[@id='loginDiv']/iframe";
	static String login_userName = "name=>email";
	static String login_passWord = "name=>password";
	static String login_Button = "id=>dologin";
	static String login_success_user = "id=>spnUid";
	static String logout_Button = "linkText=>退出";
	static String login_Error_Hint = "xpath=>//div[@class='ferrorhead']";
	
	//登录方法
	public static void login(BrowserEmulator driver,String username,String password){
		driver.enterFrame(login_frame);
		driver.type(login_userName,username);
		driver.type(login_passWord,password);
		driver.click(login_Button);
		driver.leaveFrame();
	}
	
	//获取登录错误提示
	public static String login_error_info(BrowserEmulator driver){
		driver.enterFrame(login_frame);
		String text = driver.getText(login_Error_Hint);
		driver.leaveFrame();
		return text;
	}
	
	//获取登录成功之后的用户
	public static String login_user_info(BrowserEmulator driver){
		String text = driver.getText(login_success_user);
		return text;
	}
	
	//退出方法
	public static void logout(BrowserEmulator driver){
		driver.click(logout_Button);
	}

}
