package com.toptal.stepdefinitions.ui;


import com.toptal.library.DriverClass;
import com.toptal.library.DriverManager;
import com.toptal.page.LandingPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    DriverManager driverManager = new DriverManager();

    @Before("@ui")
    public void beforeScenario(){

        driverManager.createDriver(new DriverClass());
        LandingPage lPage = new LandingPage(DriverManager.getDriver());
        lPage.loadUrl();
    }

    @After("@ui")
    public void afterScenario(){
        DriverManager.getDriver().quit();
    }
}