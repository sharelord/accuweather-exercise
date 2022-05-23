package seleniumframework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {
	private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

	protected WebDriver driver;

	protected String title;
	protected static final int DEFAULT_WAIT_4_ELEMENT = 30;
	protected static final int DEFAULT_WAIT_4_PAGE = 30;
	protected static WebDriverWait ajaxWait;
	protected long timeout = 60;

	/*
	 * @Inject
	 *
	 * @Named("framework.implicitTimeout") protected long timeout;
	 */

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOn(WebElement element) {
		logger.info("click");
		waitForElement(element);
		element.click();
	}

	/*
	 * Scroll page down pixel
	 *
	 * @Param pixel pixel to scroll down
	 */
	public void scrollDown(String pixel) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0," + pixel + ")", "");
	}

	/*
	 * Scroll page up pixel
	 *
	 * @Param pixel pixel to scroll down
	 */
	public void scrollUp(String pixel) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + pixel + ", 0)", "");
	}

	private void setImplicitWait(int timeInSec) {
		logger.info("setImplicitWait, timeInSec={}", timeInSec);
		driver.manage().timeouts().implicitlyWait(timeInSec, TimeUnit.SECONDS);
	}

	private void resetImplicitWait() {
		logger.info("resetImplicitWait");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT_4_PAGE));
	}

	public void waitFor(ExpectedCondition<Boolean> expectedCondition) {
		setImplicitWait(0);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_4_PAGE));
		wait.until(expectedCondition);
		resetImplicitWait();
	}

	public void waitForElement(WebElement element) {
		logger.info("waitForElement");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementVisible(WebElement element) {
		logger.info("waitForElement");
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Handle locator type
	public By ByLocator(String locator) {
		By result = null;
		if (locator.startsWith("//")) {
			result = By.xpath(locator);
		} else if (locator.startsWith("css=")) {
			result = By.cssSelector(locator.replace("css=", ""));
		} else if (locator.startsWith("#")) {
			result = By.id(locator.replace("#", ""));
		} else if (locator.startsWith("name=")) {
			result = By.name(locator.replace("name=", ""));
		} else if (locator.startsWith("link=")) {
			result = By.linkText(locator.replace("link=", ""));
		} else {
			result = By.className(locator);
		}
		return result;
	}

	public static String generateRandomString(int lettersNum) {
		String finalString = "";

		int numberOfLetters = 25;
		long randomNumber;
		for (int i = 0; i < lettersNum; i++) {
			char letter = 97;
			randomNumber = Math.round(Math.random() * numberOfLetters);
			letter += randomNumber;
			finalString += String.valueOf(letter);
		}
		return finalString;
	}

	public boolean verifyURL(String url) {
		boolean value = false;
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		Assert.assertTrue(currentUrl.contains(url.toLowerCase()),"Page is not opened for entered city");
		if (currentUrl.contains(url))
			return true;
		else
			return value;
	}

	public WebDriver getDriver() {
		return driver;
	}


	public WebElement waitForElementPresent(WebElement webElement, int timeOutInSeconds) {
		WebElement element;
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
			element = wait.until(ExpectedConditions.visibilityOf(webElement));
			return element;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean isElementPresent(WebElement element) {
		try {
			waitForElementVisible(element);
			element.isDisplayed();
			return true;
		}
		catch(Exception ex){ }
		return false;
	}

	public Boolean isElementPresent(String locator) {
		Boolean result = false;
		try {
			getDriver().findElement(ByLocator(locator));
			result = true;
		} catch (Exception ex) {
		}
		return result;
	}

	public void sleepExecution(int sec)
	{
		sec = sec*1000;
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int generateRandomNumber(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

		return randomNum;
	}

	public void verifyTempeartureIsWithinRange(double tempFromAPI, double tmpFromUI){
		int value = Double.compare(tempFromAPI,tmpFromUI);
		double res = 0.0;

		if(value > 0) {
			res =tempFromAPI-tmpFromUI;
			Assert.assertTrue(res<=4,"Temperature is NOT within range! OnUI: "+tmpFromUI+ " FromAPI: "+tempFromAPI);
		} else if(value < 0) {
			res = tmpFromUI-tempFromAPI;
			Assert.assertTrue(res<=4,"Temperature is NOT within range! OnUI: "+tmpFromUI+ " FromAPI: "+tempFromAPI);
		} else if(value == 0 ) {
			Assert.assertTrue(true,"Temperature is EQUAL ! OnUI: "+tmpFromUI+ " FromAPI: "+tempFromAPI);
		}
		else  {
			Assert.fail("Temperatures are not in range ! OnUI: "+tmpFromUI+ "FromAPI: "+tempFromAPI);
		}


	}

	public double convertKelvinInToCelsius(double kelvin){
		double t = kelvin-273.15;
		return Math.floor(t);
	}
}
