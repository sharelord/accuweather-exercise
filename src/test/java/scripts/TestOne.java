package scripts;

import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojos.Schema;

import java.io.*;

public class TestOne {

    WebDriver driver;
    RequestSpecification httpRequest;
    Response response;

    @BeforeMethod
    void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    void tearDown(){
       driver.close();
       driver.quit();
    }

    @Test
    void test1() throws FileNotFoundException {
        driver.get("https://www.toolsqa.com/selenium-webdriver/webdrivermanager/");
        driver.manage().window().maximize();
        System.out.println(driver.getTitle());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File f = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(f, new File("img.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
        RestAssured.basePath="?lat=28.5707841&lon=77.3271074&appid=7fe67bf08c80ded756e598d6f8fedaea";
        httpRequest = RestAssured.given();
        response = httpRequest.get();
        response = httpRequest.request(Method.GET,RestAssured.basePath);
        System.out.println(response.asString());

        Gson gson = new Gson();

        // 1. JSON file to Java object
//        Object object = gson.fromJson(new FileReader("C:\\fileName.json"), Object.class);

        // 2. JSON string to Java object
//        String json = "{'name' : 'mkyong'}";
        Schema object = gson.fromJson(response.asString(), Schema.class);
        Reader reader = new FileReader("src/test/resources/json/schema.json");

//        Schema object = gson.fromJson(reader, Schema.class);
        System.out.println(object.getMain().getTemp());
        System.out.println(object);


//        response.then().assertThat().body(matchesJsonSchemaInClasspath("schema.json")).log().all();
    }
}
