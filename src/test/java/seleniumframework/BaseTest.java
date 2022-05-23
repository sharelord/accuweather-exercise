package seleniumframework;

import com.google.gson.Gson;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

public abstract class BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
	private static final String BREAK_LINE = "</br>";
	protected String userName;
	protected String password;
	protected String browserType;
	private WebDriver driver;
	protected String applicationUrl;
	protected String applicationUrlAPI;
	protected String appKey;
	protected String uri1;
	protected String uri2;
	public static ExtentTest extentTest;
	public static ExtentReports extentReports;
	public static Method method;

	/*pages object initialization*/
	protected HomePage homePage;

	private WebDriverManager webDriverManager;
	protected RequestSpecification httpRequest;
	protected Response response;
	protected Gson gson;

	enum DriverType {
		Firefox, IE, Chrome
	}

	public BaseTest(String browser) {
		this.browserType = browser;
	}

	@BeforeSuite
	public void before() {
		extentReports = new ExtentReports("ExtentReport.html", true);
	}

	@BeforeMethod
	public void setUp(Method method) throws Exception {
		if (browserType == null) {
			browserType = Configuration.readApplicationFile("Browser");
		}
		extentTest = extentReports.startTest(this.getClass().getSimpleName(),method.getName());
		extentTest.assignAuthor("360Logica");

		userName = Configuration.readApplicationFile("username");
		password = Configuration.readApplicationFile("password");

		this.applicationUrl = Configuration.readApplicationFile("URL");
		this.applicationUrlAPI = Configuration.readApplicationFile("apiURL");
		this.applicationUrlAPI = Configuration.readApplicationFile("apiURL");
		this.appKey = Configuration.readApplicationFile("appID");
		this.uri1 = Configuration.readApplicationFile("URI1");
		this.uri2 = Configuration.readApplicationFile("URI2");

		RestAssured.baseURI = this.applicationUrlAPI;
		httpRequest = RestAssured.given();
		response = httpRequest.get();
		gson = new Gson();

		if (DriverType.Firefox.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.gecko.driver",
					getPath() + "/src//test//resources//webdriver/geckodriver");
			driver = new FirefoxDriver();
		} else if (DriverType.IE.toString().toLowerCase().equals(browserType.toLowerCase())) {
			System.setProperty("webdriver.ie.driver",
					getPath() + "//src//test//resources//webdriver/IEDriverServer.exe");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new InternetExplorerDriver(capabilities);
		}

		else if (DriverType.Chrome.toString().toLowerCase().equals(browserType.toLowerCase())) {
//			System.setProperty("webdriver.chrome.driver",
//					getPath() + "/src//test//resources//webdriver/chromedriver");
			webDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
//			options.setHeadless(true);
			driver = new ChromeDriver(options);
		} else {
			throw new Exception("Please pass valiedbrowser type value");
		}

		/*Delete cookies*/
		driver.manage().deleteAllCookies();

		/*maximize the browser*/
		getWebDriver().manage().window().maximize();

		/*open application URL*/
		getWebDriver().navigate().to(applicationUrl);
		homePage = PageFactory.initElements(getWebDriver(), HomePage.class);

	}

	@AfterMethod
	public void captureScreenShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(result.getName());
		}
		driver.quit();
		extentReports.endTest(extentTest);
	}

	@AfterClass
	public void afterMainMethod() {
		// driver.quit();
	}

	@AfterSuite
	public void tearDownSuite() {
//		 reporter.endReport();
		extentReports.flush();
		extentReports.close();
	}

	/*Return WebDriver*/
	public WebDriver getWebDriver() {
		return driver;
	}

	/*Handle child windows*/
	public String switchPreviewWindow() {
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	/* capturing screenshot*/
	public void captureScreenshot( String fileName) {
		try {
//			System.out.println(method.getClass().getName());
			String screenshotName = getFileName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".png");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = getPath();
			String screen = "file://" + path + "/screenshots/" + screenshotName + ".png";
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screen));
			Reporter.log(
					"<a href= '" + screen + "'target='_blank' ><img src='" + screen + "'>" + screenshotName + "</a>");
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	/*Report logs*/
	public void reportLog(String message) {
		message = BREAK_LINE + message;
		logger.info("Message: " + message);
		Reporter.log(message);
		extentTest.log(LogStatus.INFO, message);
	}

	// Get absolute path
	public static String getPath() {
		String path = "";
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");
		return path;
	}

	// Creating file name
	public static String getFileName(String file) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		Calendar cal = Calendar.getInstance();
		String fileName = file + dateFormat.format(cal.getTime());
		return fileName;
	}

}
