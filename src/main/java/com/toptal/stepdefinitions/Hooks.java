package com.toptal.stepdefinitions;


import com.toptal.library.DriverClass;
import com.toptal.library.DriverManager;
import com.toptal.page.LandingPage;
import com.toptal.processor.BaseTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.LinkedHashMap;

public class Hooks {

    DriverManager driverManager = new DriverManager();

    @Before("@ui")
    public void beforeScenario(){
        driverManager.createDriver(new DriverClass());
        LandingPage lPage = new LandingPage(DriverManager.getDriver());
        lPage.loadUrl();
        lPage.signIn();
    }

    @Before("@datamap")
    public static void setUp(){
        if(BaseTest.apiMap==null){
            new BaseTest().initAPIMap();
        }
        if(BaseTest.commonMap==null){
            new BaseTest().initUiMap();
        }
    }
    @After("@ui")
    public void afterScenario(){
        DriverManager.getDriver().quit();
    }
}
