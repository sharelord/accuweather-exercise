package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumframework.BasePage;

import java.util.List;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}


	@FindBy (xpath = "//input[@class='search-input']")
	private WebElement searchInputBox;

	@FindBy (xpath = "//div[@class='results-container']//div")
	private List<WebElement> searchResults;

	@FindBy (xpath = "//div[@class='results-container']")
	private WebElement resultContainer;

	@FindBy (xpath = "(//div[@class='temp'])[1]")
	private WebElement temp;

	  public void enterCityName(String cityName){
		 searchInputBox.sendKeys(cityName);
	 }

	 public double getTempFromUI(){
		 String tmp = temp.getText();
		 double d = Double.parseDouble(tmp.substring(0,tmp.length()-2));
		 return d;
	 }

	public void selectCityFromList(String cityName) {
		waitForElementPresent(resultContainer,5);
		for(WebElement elem : searchResults){
			System.out.println(elem.getText());
			if (elem.getText().contains(cityName)) {
				clickOn(elem);
				break;
			}
		}
	}


}
