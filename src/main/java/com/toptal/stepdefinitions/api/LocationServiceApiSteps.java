package com.toptal.stepdefinitions.api;

import com.toptal.processor.ApiService;
import com.toptal.processor.DataHandler;
import com.toptal.processor.DataHandlerThread;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;



public class LocationServiceApiSteps {

    private static final Logger logger = LogManager.getLogger(LocationServiceApiSteps.class);
    JSONObject jsonObject;
    ApiService apiService ;

    @And("Business details information for tradeMe")
    public void getBusinessDetails(){
        jsonObject = DataHandler.readJson();
        DataHandlerThread.setCurrentDataHandler(jsonObject);
    }

    @When("^the user searches for the data in (.*)$")
    public void searchListOfUsedCars(String apiName){
        apiService = new ApiService();
        apiService.runServiceCall(apiName);
    }
}
