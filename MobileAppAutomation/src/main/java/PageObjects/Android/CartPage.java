package PageObjects.Android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class CartPage extends ReusableMethods{

	public CartPage(AndroidDriver driver) {
//		super(driver);
		this.driver=driver;

		// TODO Auto-generated constructor stub
	}

	double Total;

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
//	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
//	WebElement VisitWebsiteButton;

	public WebElement FindWebElement(By Ele) {

		WebElement Element =   driver.findElement(Ele);
		return Element;
	}

	By totalAmount= AppiumBy.id("com.androidsample.generalstore:id/totalAmountLbl");
	By TermsButton= AppiumBy.id("com.androidsample.generalstore:id/termsButton");
	By CheckBox= AppiumBy.className("android.widget.CheckBox");
	By AlertTitle= AppiumBy.id("com.androidsample.generalstore:id/alertTitle");
	By ClosePopupButton= AppiumBy.id("android:id/button1");
	By VisitWebsiteButton= AppiumBy.id("com.androidsample.generalstore:id/btnProceed");


	public double GettingActualTotal(){

		waitForElementToBeClickable(FindElement(totalAmount), Duration.ofSeconds(10));
		String ActualTotal = FindWebElement(totalAmount).getText();
		ActualTotal = ActualTotal.substring(1);
		Total = Double.parseDouble(ActualTotal);
		return Total;
	}


	public void ClickOnCheckbox() {
		LongClickOnElement(FindWebElement(CheckBox));

	}


	public void LongClickOnTermsButton() {
		LongClickOnElement(FindWebElement(TermsButton));
	}


	public String GetAlertTitle() {
		String AlertTitleText = FindWebElement(AlertTitle).getText();
		return AlertTitleText;

	}

	public void ClickOnClosePopupButton() {

		FindWebElement(ClosePopupButton).click();
}

	public void ClickOnVisitWebsiteButton() {

		waitForElementToBeClickable(FindWebElement(VisitWebsiteButton), Duration.ofSeconds(10));
		FindWebElement(VisitWebsiteButton).click();
}


}
