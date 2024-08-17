package PageObjects.Android;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ReusableMethods {

	AndroidDriver driver;
//	public PageBase(AndroidDriver driver) {
//		this.driver=driver;
//		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
//	}

	public WebElement FindElement(By Locator) {

		WebElement Element = driver.findElement(Locator);
		return Element;
	}

	public void waitForElementToBeClickable(WebElement WE, Duration timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	    wait.until((WebDriver driver) -> {
	        try {
	            WebElement element = WE;
	            return element.isEnabled() && element.isDisplayed();
	        } catch (NoSuchElementException | StaleElementReferenceException e) {
	            return false;
	        }
	    });
	}
	public void waitForElementToBeVisible(WebElement WE, Duration timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
	    wait.until((WebDriver driver) -> {
	        try {
	            WebElement element = WE; //driver.findElement(locator);
	            return element.isDisplayed();
	        } catch (NoSuchElementException | StaleElementReferenceException e) {
	            return false;
	        }
	    });
	}


	public void LongClickOnElement(WebElement element) {

		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of ("elementId", ((RemoteWebElement) element).getId(),"duration",2000));
	}


	  public String ScrollToElement(String Text) {
		  String OriginalText="new UiScrollable(new UiSelector()).scrollIntoView(text(\""+Text+"\"))";
//		    String updatedText = OriginalText.replaceAll("WebView3", ScrollToTextValue);
		    return OriginalText;
		}

		public void ScrollToEnd() {

			boolean canScrollMore;
			do {

				 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					    "left", 100, "top", 100, "width", 200, "height", 200,
					    "direction", "down",
					    "percent", 3.0
					));
			} while (canScrollMore);
		}

		public void ScrollDown() {
((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					    "left", 100, "top", 100, "width", 200, "height", 200,
					    "direction", "down",
					    "percent", 1.0
					));
		}

		public void Scrollup() {

((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
					    "left", 100, "top", 100, "width", 200, "height", 200,
					    "direction", "up",
					    "percent", 1.0
					));


//			Map<String, Object> params = new HashMap<>();
//			params.put("left", 100);
//			params.put("top", 100);
//			params.put("width", 200);
//			params.put("height", 200);
//			params.put("direction", "up");
//			params.put("percent", 10.0);
//			driver.executeScript("mobile: scrollGesture", params);

		}

		public void SwipeAction(WebElement elementtoSwipeFrom, String Direction) {

			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
					((RemoteWebElement) elementtoSwipeFrom).getId(),
					"direction", Direction,
					"percent", 0.10));
		}

		public void WaitingFuction(WebElement Element) {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(Element));
			wait.until(ExpectedConditions.elementToBeClickable(Element));

		}

		public void PressBackButton() {
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
		}


}
