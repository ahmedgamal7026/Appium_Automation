package Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data.JsonDataReader;
import PageObjects.Android.FormPage;
import Utils.TestBase;

public class FormPageValidationTests extends TestBase {



	@BeforeMethod(alwaysRun = true)
	public void ReturnToHomePage() throws InterruptedException {
//		//Return To Home Page Before Running any Test
//
////		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.SplashActivity");
//		HashMap<String, String> params = new HashMap<>();
//		params.put("intent", "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
//		// Execute the command
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", params);

		String packageName = ( driver.getCurrentPackage());
		driver.terminateApp(packageName);
		driver.activateApp(packageName);
		Thread.sleep(4000);

	}


	@Test(priority=1,groups = {"Smoke"})
	public void FormAlertValidation() throws InterruptedException {

		Thread.sleep(2000);
		FormPage FP = new FormPage(driver);
		FP.ClickOnLetsShopButton();
		WebElement AlertPopUp=FP.ReturnPopUpElement();
		String AlertText = AlertPopUp.getText();
		System.out.println(AlertText);
		Assert.assertEquals(AlertText, "Please enter your name");

	}


	@Test(priority=2,groups = {"Smoke"},dataProvider = "dataFromJson")
	public void FormPositiveScenario(HashMap<String,String> input  ) throws InterruptedException  {

		FormPage FP = new FormPage(driver);
		Thread.sleep(2000);
		FP.InsertNameField(input.get("name"));
		FP.SelectCountry(input.get("Country"));
		FP.ClickOnLetsShopButton();
		List <WebElement> AlertPopUps=FP.GetAllAlertElements();
		int AlertsCount = AlertPopUps.size();
		Assert.assertEquals(AlertsCount,0);
		Thread.sleep(2000);


	}


	@DataProvider(name = "dataFromJson")
	public Object[][] UserData() throws IOException {


		List<HashMap<String,String>> Data = JsonDataReader.ReadingJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\TestData.json");
		return new Object[][] {{Data.get(0)},{Data.get(1)}};



	}



}
