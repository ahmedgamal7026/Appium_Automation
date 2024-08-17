package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;



public class TestBase {

	AppiumDriverLocalService Service;
	protected static  AndroidDriver driver ;

	@BeforeClass(alwaysRun = true)
	public void ConfigureAppium() throws URISyntaxException, IOException {

		//Appium Code ==> Appium Server ==> Mobile

		//the below Row is used to Start the Appium Server automatically once the test case starts execution, without manual effort
//		File MainJsFileToStartAppiumServer = new File("C://Users//aothman//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
//		 Service = new AppiumServiceBuilder().withAppiumJS(MainJsFileToStartAppiumServer)
//				.withIPAddress("127.0.0.1").usingPort(4723).build();
//		Service.start();

		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\GlobalData.properties");
		prop.load(fis);
		/*System.getProperty("IPAddress")!=null? checks if the value is sent during runtime command or not, if it is not null*/
		/*this statement will be executed (System.getProperty("IPAddress"))*/
		/*else, it will execute the third statement which is after the colon which is : prop.getProperty("IPAddress")*/
		String IP=System.getProperty("IPAddress")!=null? (System.getProperty("IPAddress")): prop.getProperty("IPAddress");
//		String IP= prop.getProperty("IPAddress");
		String PORT= prop.getProperty("Port");
		String EmulatorName= prop.getProperty("AndroidDeviceName");

		StartServerice(IP,Integer.parseInt(PORT));


		//UiAutomator2Options object is used to Set Capabilities and pass it to the Driver as an argument
		//so the server can understand what is the server url, and which app i will Automate
		UiAutomator2Options Options = new UiAutomator2Options();
		Options.setChromedriverExecutable(System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		Options.setApp(System.getProperty("user.dir")+"\\Resources\\General-Store.apk"); //Required App Path
		Options.setDeviceName(EmulatorName); //Name of the Created device Emulator, you Can Get it Through CMD by (emulator -list-avds), then Run the needed one by "emulator -avd EMULATOR_Device_NAME")
		driver = new AndroidDriver(Service.getUrl(),Options);
	}

	
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


public AppiumDriverLocalService StartServerice(String IPAddress, int Port) {

	//the below Row is used to Start the Appium Server automatically once the test case starts execution, without manual effort
	File MainJsFileToStartAppiumServer = new File("C://Users//aothman//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");
	 Service = new AppiumServiceBuilder().withAppiumJS(MainJsFileToStartAppiumServer)
			.withIPAddress(IPAddress).usingPort(Port).build();
	Service.start();
	return Service;
}


	@AfterClass(alwaysRun = true)
	public void AfterTest() {
		//After Test
		driver.quit();  // quit The Driver
//		Service.stop();  //Stop the Server

	}


	public String GetScreenshotPath( String testcaseName, AppiumDriver driver) throws IOException {
		
 	   // Check if the screenshot file already exists and delete it
//		String filePath = (System.getProperty("user.dir")+"\\Reports\\");
//     File existingScreenshot = new File(filePath);
//     if (existingScreenshot.exists()) {
//         try {
//             Files.delete(existingScreenshot.toPath());
//             System.out.println("Existing Old screenshot deleted: " + filePath);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
		File ScreenShotSource = driver.getScreenshotAs(OutputType.FILE);   //capture the screenshot
		String DestinationFile=System.getProperty("user.dir")+"//Reports//"+testcaseName+".png"; // this is the destination i need to add the ss in
		System.out.println("Screenshot will be in the following path : "+ DestinationFile);
		FileUtils.copyFile(ScreenShotSource, new File(DestinationFile));  // copy from source to destination
		return DestinationFile;  //return the destination folder to attach the ss to the extent report
	}


//below function is working fine 
//	    public static void takeScreenshot(String filePath) {
//
//
//	    	   // Check if the screenshot file already exists and delete it
//	        File existingScreenshot = new File(filePath);
//	        if (existingScreenshot.exists()) {
//	            try {
//	                Files.delete(existingScreenshot.toPath());
//	                System.out.println("Existing Old screenshot deleted: " + filePath);
//	            } catch (IOException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        // Cast the driver to TakesScreenshot
//	        TakesScreenshot ts = driver;
//	        // Get the screenshot as a file
//	        File screenshot = ts.getScreenshotAs(OutputType.FILE);
//	        try {
//	            // Copy the screenshot to the desired location
//	        	Path source = screenshot.toPath();
//	        	Path Destination = Paths.get(filePath);
//
//	            Files.copy(source, Destination);
//	            System.out.println("Screenshot saved at: " + filePath);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//
//	    }

}
