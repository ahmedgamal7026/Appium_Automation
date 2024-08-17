package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data.ExcelReader;
import PageObjects.Android.CartPage;
import PageObjects.Android.FormPage;
import PageObjects.Android.ItemsPage;
import Utils.TestBase;

public class ValidateTotalPurchaseAmount_Native extends TestBase {

	String Price1 = "";
	String Price2 = "";
	double Sum;
	double Total;


//	By CheckBox = AppiumBy.className("android.widget.CheckBox");


	@DataProvider(name = "data")
	public Object[][] UserData() throws IOException {

		ExcelReader ER = new ExcelReader();
		return ER.getExcelData(0);

	}
	
	@Test(dataProvider = "data", groups = {"Regression"})
	public void ValidateTotalPurchaseAmount(String Country,String Name) throws InterruptedException {


		Thread.sleep(4000);
		FormPage FP= new FormPage(driver);
		ItemsPage IP= new ItemsPage(driver);

		FP.SelectCountry(Country);
		FP.InsertNameField(Name);
		FP.ClickOnLetsShopButton();
		IP.SCrollToJordan6Shoes("Jordan Lift Off");//Jordan Lift Off  //Jordan 6 Rings
//		IP.ScrollDown();//----------------------------
		IP.Scrollup();//----------------------------
		Thread.sleep(3000);

		int ItemsCount= IP.GetItemsCount();
		System.out.println(ItemsCount);
		Sum = IP.GettingTotalPrices(ItemsCount);
		IP.ClickOnCartButton();

		CartPage CP= new CartPage(driver);
		Thread.sleep(2000);

		Total =CP.GettingActualTotal();
		Assert.assertEquals(Sum, Total);
		System.out.println("The Summation is Valid");
		CP.ClickOnCheckbox();
//		driver.findElement(CheckBox).click();
		CP.LongClickOnTermsButton();

		String PopUpTitle = CP.GetAlertTitle();

		Assert.assertEquals(PopUpTitle, "Terms Of Conditions");
		CP.ClickOnClosePopupButton();
//		Thread.sleep(2000);
		CP.ClickOnVisitWebsiteButton();
		Thread.sleep(2000);
		IP.GetAlLContexts();
//		IP.SwitchToContext("WEBVIEW_com.androidsample.generalstore"); //switching to Web view
//		ChromeBrowserGoogleSearchPage GP = new ChromeBrowserGoogleSearchPage(driver);
//		GP.InsertGoogleSerachFieldValue("Appium Automation Learning");
//		Thread.sleep(2000);
//		driver.pressKey(new KeyEvent(AndroidKey.BACK));
//		driver.context("NATIVE_APP");         //switching to native app again
//		Thread.sleep(3000);

	}

}
