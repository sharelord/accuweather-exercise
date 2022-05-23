package scripts;

import constants.GlobalConstant;
import dataproviders.DataProviders;
import io.restassured.http.Method;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import pojos.City;
import pojos.Schema;
import seleniumframework.BaseTest;

public class HomeScript extends BaseTest {

	@Factory(dataProvider = "Config", dataProviderClass = DataProviders.class)
	public HomeScript(String browser) {
		super(browser);
	}

	@Test
	public void testTemperatureFromUIAndAPI() throws Exception {

		double lat = 0, lon = 0;
		String apiEndPointCity = uri2+ GlobalConstant.CityNames.Pune+ ",IN=&appid="+appKey;

		reportLog("User is required to login using a valid IsoMetrix username and password.");
		homePage.enterCityName(GlobalConstant.CityNames.Pune.toString());
		homePage.selectCityFromList(GlobalConstant.CityNames.Pune.toString());
		homePage.sleepExecution(2);
		homePage.verifyURL(GlobalConstant.CityNames.Pune.toString());
		double tmpFromUI = homePage.getTempFromUI();
		homePage.convertKelvinInToCelsius(tmpFromUI);

		response = httpRequest.request(Method.GET, apiEndPointCity);
		System.out.println(response.asString());
		City[] city = gson.fromJson(response.asString(), City[].class);
		System.out.println(city[0].getLat());
		System.out.println(city[0].getLon());
		lat=city[0].getLat();
		lon=city[0].getLon();
		String apiEndPoint = uri1+"?lat="+lat+"&lon="+lon+"&appid="+appKey;

		response = httpRequest.request(Method.GET, apiEndPoint);
		System.out.println(response.asString());
		Schema object = gson.fromJson(response.asString(), Schema.class);
		double tmpFromAPI = homePage.convertKelvinInToCelsius(object.getMain().getTemp());
		System.out.println(homePage.convertKelvinInToCelsius(object.getMain().getTemp()));
		homePage.verifyTempeartureIsWithinRange(tmpFromAPI,tmpFromUI);

	}

}
