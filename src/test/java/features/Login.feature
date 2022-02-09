Feature: Verify the Weather information of Various Cities

@VerifyWeatherinfo
Scenario Outline: Verify Weather information of Various Cities through Accuwether wesite 
Given User Enters the Url in the browser 
Then  Landing page is populated
When User enters the City name <Cityname> and Selects <Citynamefull> from autosuggestions
Then Weather information of that City is displayed
Examples:
| Cityname  | Citynamefull |
| Chandigarh | Chandigarh, Chandigarh, IN |

Scenario Outline: Verify Geolocation information of Various Cities through OpenWeather API 
Given Get Geolocation  of "<City>" in Units "metric"
When "getGeolocationAPI" with "GET" http request
Then the API call got success with status code 200
And verify Logintude and Latitude maps to "<City>"	
Examples:
| City  |
| Chandigarh  |
Scenario Outline: Verify Weather information of Various Cities through OpenWeather API
Given Get Weather information using Lon and Lat informtion from "getWeatherAPI" in Units "metric"
Then the API call got success with status code 200
And verify Weather Information  maps to "<City>"
	
Examples:
| City  |	
| Chandigarh|
Scenario Outline: Verify and compare the Weather information of varios cities from Accuweaher Website to Openweather Api
Given We have Weather informtion "<City>" from Accuweather and OpenWeatherAPI
When User verifies and Compares data from both the Sources
Then The Conclusion of Comparition is completed 
Examples:
| City  |	
| Chandigarh | 


@VerifyWeatherinfo
Scenario Outline: Verify Weather information of Various Cities through Accuwether wesite 
Given User Enters the Url in the browser 
Then  Landing page is populated
When User enters the City name <Cityname> and Selects <Citynamefull> from autosuggestions
Then Weather information of that City is displayed
Examples:
| Cityname  | Citynamefull |
| Mumbai | Mumbai, Maharashtra, IN |

Scenario Outline: Verify Geolocation information of Various Cities through OpenWeather API 
Given Get Geolocation  of "<City>" in Units "metric"
When "getGeolocationAPI" with "GET" http request
Then the API call got success with status code 200
And verify Logintude and Latitude maps to "<City>"	
Examples:
| City  |
| Mumbai  |
Scenario Outline: Verify Weather information of Various Cities through OpenWeather API
Given Get Weather information using Lon and Lat informtion from "getWeatherAPI" in Units "metric"
Then the API call got success with status code 200
And verify Weather Information  maps to "<City>"
	
Examples:
| City  |	
| Mumbai|
Scenario Outline: Verify and compare the Weather information of varios cities from Accuweaher Website to Openweather Api
Given We have Weather informtion "<City>" from Accuweather and OpenWeatherAPI
When User verifies and Compares data from both the Sources
Then The Conclusion of Comparition is completed 
Examples:
| City  |	
| Mumbai | 







