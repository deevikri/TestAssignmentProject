package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccuweatherHomePage {
	public WebDriver driver;
	
	public AccuweatherHomePage(WebDriver driver)
	{
		this.driver=driver;
	}

	By search=By.cssSelector("input[name=query]");
	
	By CurrentTemperature=By.xpath("//div[@class='card-content']//div[@class='display-temp']");
			
	By MinTemperature=   By.xpath("//h2[contains(text(),'Night')]//parent::div//div[@class='temperature']");
	
	By addToCart=By.xpath("//button[contains(text(),'ADD TO CART')]");
	
	By LandingPageLogo=By.xpath("//a[contains(@class,'header-logo')]/img");
	

 
	  
	
	

	public WebElement getSearch()
	{
		return driver.findElement(search);
	}
	
	public WebElement getCurrenttemperature()
	{
		return driver.findElement(CurrentTemperature);
	}
	public WebElement getmintemperature()
	{
		return driver.findElement(MinTemperature);
	}
	
	
	public WebElement getlandingpagelogo()
	{
		return driver.findElement(LandingPageLogo);
	}
}
