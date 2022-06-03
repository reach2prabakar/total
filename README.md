# TopTal_AutomationAssesement 

> TO validate the http://automationpractice.multiformis.com UI and 
> TO validate Map service (mapbox) with the given Acceptance Criteria.


## Video Demo of the project
Short video of running the UI ,API and load test is attached to the ProjectVideos branch 
> Automation_RECPart1.mp4<br/>
> Automation_RECPart2.mp4<br/>
> loadTestPart1.mp4<br/>
> loadTestPart2.mp4<br/>


## TOOLS
[JAVA V_1.8 or greater](https://www.java.com/en/download/)<br/>
[Maven Build](https://maven.apache.org/download.cgi)<br/>
[Cucumeber BDD Framework](https://mvnrepository.com/artifact/io.cucumber/cucumber-java/6.3.0)<br/>
[Simple JsonParser , Rest Assured](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)

## Project Description
> This project is to login to ecomm application, add the product to cart and complete checkout process
<br/>to validate the mapbox services by sending location information and updating,deleting,adding data to the mapboox api

## Project Highlights
> Project is build on BDD framework with POM and  part of modular approach. .<br/>
> Selenium/Java is used for code<br/>
> Validation of API is done via Rest Assured <br/>
> Log are entered using slf4j<br/>
>Assertion is done thorugh hamcrest assertion <br/>
>Report -  
	> cucumbet HTML report <br/>



## Installation/Pre conditions for Running the test

Java 1.7 or higher
<br/>Maven 
<br/>Intellij/Eclipse (Intellij prferable) / Command promt to run the test
<br/>Set Java_Home and Maven_Home in environmental variables

To check if Java and maven are installed in your Machine:

```sh
Java -version
Mvn -version
```

## Creating testdata
To create API test data, use jsonTestData.json file in test/resource
<br/> "uri": "https://api.mapbox.com",
<br/>"geocoding": { 
  <br/>    "endpoint":"/v5/mapbox.places/chester.json",
   <br/> "method" : "get",
  <br/>      "auth" : "noAuth",
   <br/> "header": "",
  <br/>  "requestBody": ""
  <br/>}
  <br/>"geocoding":  - is your test api name to pass in feature file

 
  
  ## Validations
  
  > All API uri response code is validated for status code 200
  
  > The response data is then asserted with the acceptance criteria 
  
  > The UI data is asserted with the business requirement, if any step fail the test scenario will fail.
  
  > One scenario will get failed as the test data does not match with the details in UI
  

## How to run the test

Download the code from GITHUB 

```sh
git pull origin master 
<br/>https://git.toptal.com/screening/Prabhakaran-Sundaramoorthy.git
```

Open the project in any IDE (Intellij preferred)
You can right click on the feature file and select Run (not preffered to run in suite level)

to run the whole suite , set up cucumber-java configuration and run the test

If you want to run the project as maven Build 

Open the command promt, Navigate to your project folder
```sh
C:\Users> cd path to your folder
C:\Users\Projectfolder> mvn clean install
```

## Reporting

For greater understanding cucumber JVM is plugged in with the framework, However the inbuild cucumber.Html report is avaialble

Once all the test is executed,
<br/>Open the project folder ->\target\cucumber-reports\advanced-reports\cucumber-html-reports
>### overview-features.html



## Contributing

1. Fork it (<https://github.com/yourname/yourproject/fork>)
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request

