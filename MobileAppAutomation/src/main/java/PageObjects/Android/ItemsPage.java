package PageObjects.Android;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class ItemsPage extends ReusableMethods {

	public ItemsPage(AndroidDriver driver) {
//		super(driver);
		this.driver=driver;

	}

	String Price1 = "";
	String Price2 = "";
	double Sum;

	//	By CartButton = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");
	//	By totalAmount = AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");
	//	By TermsButton = AppiumBy.id("com.androidsample.generalstore:id/termsButton");
	//	By CheckBox = AppiumBy.className("android.widget.CheckBox");
	//	By AlertTitle = AppiumBy.id("com.androidsample.generalstore:id/alertTitle");
	//	By VisitWebSiteButton = AppiumBy.id("android:id/button1");

//	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
//	WebElement CartButton;
	//	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	//	WebElement totalAmount;
	//	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	//	WebElement TermsButton;
	//	@AndroidFindBy(className="android.widget.CheckBox")
	//	WebElement CheckBox;
	//	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
	//	WebElement AlertTitle;
	//	@AndroidFindBy(id="android:id/button1")
	//	WebElement ClosePopupButton;
	//
	//	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	//	WebElement VisitWebsiteButton;

	By CartButton = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");
	By ItemsElements = AppiumBy.id("com.androidsample.generalstore:id/productName");

	public void SCrollToJordan6Shoes(String Text) {

		driver.findElement(AppiumBy.androidUIAutomator(ScrollToElement(Text))).click();
	}

	public int GetItemsCount() {
		int ItemsCount = driver.findElements(ItemsElements).size();
		return ItemsCount;

	}

	public void GetAlLContexts() {

		Set<String> Contexts = driver.getContextHandles();
		for (String ContextName : Contexts) {
			System.out.println(ContextName);
		}
	}

	public void SwitchToContext(String Context) {

		driver.context(Context); // switching to Web view

	}

	public double GettingTotalPrices(int ItemsCount) {
		for (int i = 0; i < ItemsCount; i++) {

			String Product = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName")).get(i)
					.getText();
			if (Product.equalsIgnoreCase("Jordan 6 Rings")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				Price1 = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).get(i)
						.getText();
				Price1 = Price1.substring(1);
				System.out.println(Price1);
			} else if (Product.equalsIgnoreCase("Jordan Lift Off")) {
				driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				Price2 = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productPrice")).get(i)
						.getText();
				Price2 = Price2.substring(1);
				System.out.println(Price2);

			}
		}
		double P1 = Double.parseDouble(Price1);
		double P2 = Double.parseDouble(Price2);
		Sum = P1 + P2;
		System.out.println("The Total is : " + Sum);
		return Sum;

	}

	public void ClickOnCartButton() {
FindElement(CartButton).click();
//		CartButton.click();
	}

	@Override
	public void ScrollDown() {
		((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 100,
				"width", 200, "height", 200, "direction", "down", "percent", 1.0));
	}

}
