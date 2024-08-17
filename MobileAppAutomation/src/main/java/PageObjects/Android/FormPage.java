package PageObjects.Android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
public class FormPage  extends ReusableMethods {

//	 AndroidDriver driver;
	public FormPage(AndroidDriver driver) {
//		super(driver);
		this.driver=driver;
	}




//	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
//	private WebElement NameField;

//	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
//	private WebElement CountryDDL;

//	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
//	private WebElement LetsShopButton;


//	public static WebElement FindWebElement(By Ele) {
//
//		WebElement Element =   driver.findElement(Ele);
//		return Element;
//	}

	By NameField= AppiumBy.id("com.androidsample.generalstore:id/nameField");
	By CountryDDL= AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry");
	By LetsShopButton= AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");
	By AlertPopUp= AppiumBy.xpath("//android.widget.Toast[@text=\"Please enter your name\"]");
	By All_AlertPopUps= AppiumBy.xpath("//android.widget.Toast");



	public void InsertNameField(String Name){
		FindElement(NameField).sendKeys(Name);
//		NameField.sendKeys(Name);
		driver.hideKeyboard();
	}
	public void SelectCountry(String Text) {
		//		Select Country=new Select(CountryDDL);
		waitForElementToBeClickable(FindElement(CountryDDL),Duration.ofSeconds(10));
		waitForElementToBeVisible(FindElement(CountryDDL),Duration.ofSeconds(10));

		FindElement(CountryDDL).click();
		driver.findElement(AppiumBy.androidUIAutomator(ScrollToElement(Text))).click();
	}


	public void ClickOnLetsShopButton() {
		waitForElementToBeClickable(FindElement(LetsShopButton),Duration.ofSeconds(10));
		FindElement(LetsShopButton).click();

	}


	public WebElement ReturnPopUpElement() {
		return FindElement(AlertPopUp);

	}

	
	public List<WebElement> GetAllAlertElements() {
		 return driver.findElements(All_AlertPopUps);
	}
}
