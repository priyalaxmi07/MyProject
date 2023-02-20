package Testcases;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import PageObjectModel.LoginPageObjects;
import Resource.Baseclass;
import Resource.CommonMethods;
import Resource.Constant;
public class verifyLogin extends Baseclass {
	private SoftAssert a;
	@Test(dataProvider="testData")
	public void login(String username, String password) throws IOException {
		LoginPageObjects obj = new LoginPageObjects(driver);
		 CommonMethods.handleAssertions(driver.getCurrentUrl(),"https://login.salesforc.com/");
		obj.enterUsername().sendKeys(username);
		obj.enterPassword().sendKeys(password);
		obj.clickOnLogin().click();
		CommonMethods.handleAssertions(obj.errortext().getText(), Constant.expectedErrormessage);

	/*	SoftAssert a = new SoftAssert();
		String actual = obj.errortext().getText();
		String expected = "Please check your password. If you still can't log in, contact your Salesforce administrator.";
		a.assertEquals(actual, expected, "Error message is not matching");
	*/	a.assertAll();
	}
	@DataProvider
	public Object[][] testData() {
		Object[][] data = new Object[2][2];
		data[0][0] = Constant.username1;
		data[0][1] = Constant.password1;
		data[1][0] = Constant.username2;
		data[1][1]=Constant.password2;
		return data;
	}
}