package com.toptal.stepdefinitions.api;

import com.toptal.processor.ApiService;
import com.toptal.processor.DataHandler;
import com.toptal.processor.DataHandlerThread;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LocationServiceApiSteps {

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceApiSteps.class);
    JSONObject jsonObject;
    ApiService apiService ;

    @Given("Data information for mapbox")
    public void getBusinessDetails(){
        jsonObject = DataHandler.readJson();
        DataHandlerThread.setCurrentDataHandler(jsonObject);
        logger.debug("json file is read correctly");
    }
    @When("^the user searches for the data in (.*)$")
    public void searchDataList(String apiName){
        apiService = new ApiService();
        apiService.runServiceCall(apiName);
    }
}
