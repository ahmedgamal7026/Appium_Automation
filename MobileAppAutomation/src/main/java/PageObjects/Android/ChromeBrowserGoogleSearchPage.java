package PageObjects.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.android.AndroidDriver;

public class ChromeBrowserGoogleSearchPage extends ReusableMethods{

	public ChromeBrowserGoogleSearchPage(AndroidDriver driver) {
//		super(driver);
		this.driver=driver;
		}


//	@AndroidFindBy(xpath="//android.widget.EditText")
//	WebElement SearchField;
	By SearchField=By.name("q");


	public void InsertGoogleSerachFieldValue(String SearchValue) {

//		SearchField.sendKeys(SearchValue);
//		SearchField.sendKeys(Keys.ENTER);
		FindElement(SearchField).sendKeys(SearchValue);
		FindElement(SearchField).sendKeys(Keys.ENTER);
	}

}
