package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.Range;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import pageObjects.AccuweatherHomePage;

import base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GetWeatherinfo;
import weatherTest.API.APIResources;
import weatherTest.API.Utils;





public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;		
	Response response;
	GetWeatherinfo Response1;	
	AccuweatherHomePage h;
	public WebDriver driver;	
	static String Longitude;
	static String Latitude;
	static Double Temperature_UI;
	static String Temperature;
	static Double Temperature_API;
	Boolean Result;
	
	SoftAssert Soft_Assert = new SoftAssert(); 
	
@Given("User Enters the Url in the browser")
public void user_Enters_the_Url_in_the_browser() throws IOException {
// Write code here that turns the phrase above into concrete actions
//	    throw new cucumber.api.PendingException();      
	
	
	 driver= BaseTest.getDriver();
	 
	 driver.manage().window().maximize();
	 
	    
	}
@Then("^Landing page is populated$")
public void Landing_page_is_populated()  {
	
	h = new AccuweatherHomePage(driver);
	h.getlandingpagelogo().isDisplayed();
	Soft_Assert.assertEquals(h.getlandingpagelogo().isDisplayed(), true);	
}

@When("^User enters the City name (.+) and Selects (.+) from autosuggestions$")
public void user_enters_the_city_name(String cityname, String fullcityname) throws NoSuchElementException, IOException  {
	
	
	h.getSearch().sendKeys(cityname);
	
	Actions Act = new Actions(driver);
	Act.sendKeys(Keys.ENTER).perform();
	
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	
	
	WebElement Results =driver.findElement(By.xpath("//div[contains(@class,'locations-list content')]"));	
	
	 List<WebElement> optionsToSelect = Results.findElements(By.tagName("a"));

	    for(WebElement option : optionsToSelect){
	        System.out.println(option);
	        if(option.getText().equals(fullcityname)) {
	            System.out.println("Trying to select: "+fullcityname);
	            option.click();
	            break;
	        }
	    }  
	    
	try {
		driver.switchTo().frame("google_ads_iframe_/6581/web/in/interstitial/admin/search_0");
		driver.findElement(By.id("dismiss-button")).click();
	
	}catch(Exception e) {
		
		System.out.println("There is no Iframe Adv");
		
	}
	}	 


@Then("Weather information of that City is displayed")
public void wether_information_of_that_city_is_displayed() throws Throwable {

	Thread.sleep(500);
	WebDriverWait wait = new WebDriverWait(driver,50);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Current Weather')]")));
	driver.findElement(By.xpath("//span[contains(@class,'cur-con-weather')]/span[text()='More Details']")).click();
	Temperature = h.getCurrenttemperature().getText();
	Temperature = (Temperature.replace("°C"," ")).trim();
	Temperature_UI =  Double.parseDouble(Temperature);
	System.out.println("The Temperature of the City is"+Temperature_UI);
	
	driver.close();
  
}

@Given("Get Geolocation  of {string} in Units {string}")
public void get_Geolocation_of(String City, String Units) throws IOException {
	    // Write code here that turns the phrase above into concrete actions	
		 
		 res=given().spec(requestSpecification()).queryParams("q", City,"unit",Units);
		
	}

@When("{string} with {string} http request")
public  void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
        //constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());	
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("GET"))
		response=res.when().get(resourceAPI.getResource());	    
		
}



@Then("the API call got success with status code {int}")
public void the_API_call_got_success_with_status_code(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
	Soft_Assert.assertEquals(response.getStatusCode(),200);
		
	
	}
	
@And("verify Logintude and Latitude maps to {string}")
	public void verify_Logintude_and_Latitude_maps_to_using(String expectedName) throws IOException {
		// requestSpec
		
		
		Latitude=getJsonPath(response,"lat");		
		Longitude=getJsonPath(response,"lon");		
		Latitude = Latitude.replace("["," ");
		Latitude = (Latitude.replace("]"," ")).trim();
		Longitude = Longitude.replace("["," ");
		Longitude = (Longitude.replace("]"," ")).trim();
			 

		String Expectedname=getJsonPath(response,"name");
		Soft_Assert.assertEquals(Expectedname,"[Chandigarh]","Correct city name received in the Response");
		 
	    
	}
	

@Given("Get Weather information using Lon and Lat informtion from {string} in Units {string}")
public void get_Weather_information_using_Lon_and_Lat_informtion_from_in_Units(String resource, String Units) {
    // Write code here that turns the phrase above into concrete actions
//    throw new cucumber.api.PendingException();
	APIResources resourceAPI=APIResources.valueOf(resource);
	System.out.println(resourceAPI.getResource());
	
	try {
		res=given().spec(requestSpecification()).queryParams("lat",Latitude,"lon",Longitude,"units",Units);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	user_calls_with_http_request(resource, "GET");
	Response1=res.when().get(resourceAPI.getResource()).as(GetWeatherinfo.class);
}


   @And("verify Weather Information  maps to {string}")
	public void verify_Weather_Information_maps_to(String city) {
	    // Write code here that turns the phrase above into concrete actions
    
		Temperature_API =Response1.getMain().getTemp();
		System.out.println(Temperature_API);
		 String Expectedname=getJsonPath(response,"name");
		 Soft_Assert.assertEquals(Expectedname,"[Chandigarh]","Correct city name received in the Response");
	}



@Given("We have Weather informtion {string} from Accuweather and OpenWeatherAPI")
public void we_have_Weather_informtion_from_Accuweather_and_OpenWeatherAPI(String City) throws AssertionError {   // Write code here that turns the phrase above into concrete actions/    
	
	
	
	System.out.println("Temerature of " + City + " in OpernweatherAPI is " + Temperature_API);
	System.out.println("Temerature of " + City + " in Accuweather is " + Temperature_UI);
}

	@When ("User verifies and Compares data from both the Sources")
public void User_verifies_and_Compares_data_from_both_the_Sources() {
	
	 Integer Difference = (int) (Temperature_API - Temperature_UI);
	  
	  Range<Integer> myRange = Range.between(-3, 3);
	  if (myRange.contains(Difference)){			  
		
		  Soft_Assert.assertTrue(true,"The Difference between Temperatures from Two sources is in Permissible Range , Hence our Test Passed");
	  }
	  
	  else {
		  
		  Assert.fail("The Difference between Temperatures from Two sources is not in Permissible Range , Hence our Test failed");
		  
	  }
	  
	
	
		
	}

@Then("The Conclusion of Comparition is completed")
public void Result_of_Comparition_is_completed() {
	
	  System.out.println("Comparisition of Temperatures is completed");
	
}


}
