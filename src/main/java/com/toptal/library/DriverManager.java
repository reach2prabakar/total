package com.toptal.library;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private WebDriver driver;
    protected static ThreadLocal<WebDriver> driverthread = new ThreadLocal<>();

    public DriverManager(){}

    public static WebDriver getDriver(){
        if (driverthread == null) {
            throw new RuntimeException("Driver object is not instantiated, createWebdriver() in Driverclass should be called");
        }
        return driverthread.get();
    }

    public static void setDriver(WebDriver driver){driverthread.set(driver);}

    public void createDriver(Driver driver){
      this.driver =  driver.createDriver();
      setDriver(this.driver);
    }

}
