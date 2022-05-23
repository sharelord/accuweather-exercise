package scripts;

import constants.GlobalConstant;
import dataproviders.DataProviders;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pojos.City;
import pojos.Schema;
import seleniumframework.BaseTest;

public class HomeScriptTest extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public HomeScriptTest(String browser) {
		super(browser);
	}

	@BeforeClass
	public void openBrowser() throws Exception {
		setUp();
	}

	@Test
	public void testTemperatureFromUIAndAPI() {

		double lat = 0, lon = 0;
		String apiEndPointCity = uri2+ GlobalConstant.CityNames.Pune+ ",IN=&appid="+appKey;

		reportLog("User is entering city name");
		homePage.enterCityName(GlobalConstant.CityNames.Pune.toString());

		reportLog("User is selecting city from list of cities");
		homePage.selectCityFromList(GlobalConstant.CityNames.Pune.toString());
		homePage.sleepExecution(5);

		reportLog("Validate the URL contains City name");
		homePage.verifyURL(GlobalConstant.CityNames.Pune.toString());
		reportLog("Get temp from UI");
		double tmpFromUI = homePage.getTempFromUI();

		reportLog("Get Latitude and Longitude details from API");
		response = httpRequest.request(Method.GET, apiEndPointCity);
		System.out.println(response.asString());
		City[] city = gson.fromJson(response.asString(), City[].class);
		lat=city[0].getLat();
		lon=city[0].getLon();
		String apiEndPoint = uri1+"?lat="+lat+"&lon="+lon+"&appid="+appKey;

		reportLog("Get tem details from API");
		response = httpRequest.request(Method.GET, apiEndPoint);
		Schema object = gson.fromJson(response.asString(), Schema.class);

		double tmpFromAPI = homePage.convertKelvinInToCelsius(object.getMain().getTemp());
		reportLog("Validate the temp details");
		homePage.verifyTempeartureIsWithinRange(tmpFromAPI,tmpFromUI);
	}

	@Test(dataProviderClass = DataProviders.class, dataProvider = "getCityDetails")
	public void testTemperatureFromUIAndAPIForMultipleCities(String cityName) {
		getWebDriver().get(applicationUrl);
		double lat = 0, lon = 0;
		String apiEndPointCity = uri2+ cityName+ ",IN=&appid="+appKey;

		reportLog("User is entering city name: "+cityName);
		homePage.enterCityName(cityName);

		reportLog("User is selecting city from list of cities");
		homePage.selectCityFromList(cityName);
		homePage.sleepExecution(5);

		reportLog("Validate the URL contains City name");
		homePage.verifyURL(cityName);
		reportLog("Get temp from UI");
		double tmpFromUI = homePage.getTempFromUI();

		reportLog("Get Latitude and Longitude details from API: "+apiEndPointCity);
		response = httpRequest.request(Method.GET, apiEndPointCity);
		System.out.println(response.asString());
		City[] city = gson.fromJson(response.asString(), City[].class);
		lat=city[0].getLat();
		lon=city[0].getLon();
		String apiEndPoint = uri1+"?lat="+lat+"&lon="+lon+"&appid="+appKey;

		reportLog("Get tem details from API: "+apiEndPoint);
		response = httpRequest.request(Method.GET, apiEndPoint);
		Schema object = gson.fromJson(response.asString(), Schema.class);

		double tmpFromAPI = homePage.convertKelvinInToCelsius(object.getMain().getTemp());
		reportLog("Validate the temp details on UI: "+tmpFromUI+" API: "+tmpFromAPI);
		homePage.verifyTempeartureIsWithinRange(tmpFromAPI,tmpFromUI);
	}

}
