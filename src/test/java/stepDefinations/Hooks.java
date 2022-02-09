package stepDefinations;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import io.cucumber.java.*;




public class Hooks {

		
		@Before("@VerifyWeatherinfo")
			public void beforevaldiation()
			{
				System.out.println("Verificaion starting");
			}
		
		@After("@VerifyWeatherinfo")
		public void aftervaldiation()
		{
			System.out.println("Verification Ended");
		}
		
		
		
	
}

	

