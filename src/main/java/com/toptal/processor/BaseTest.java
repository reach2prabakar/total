package com.toptal.processor;

import com.toptal.library.DriverManager;
import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    public WebDriver driver;
    public PropertyReader propertyReader;

    public BaseTest(){
        driver = DriverManager.getDriver();
        propertyReader = new PropertyReader();
    }

}
