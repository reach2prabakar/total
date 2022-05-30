package com.toptal.processor;

import com.toptal.library.DriverManager;
import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;

public class BaseTest {

    public WebDriver driver;
    public PropertyReader propertyReader;

    public static LinkedHashMap<String,String> commonMap;

    public BaseTest(){
        driver = DriverManager.getDriver();
        propertyReader = new PropertyReader();
    }

    public void initmap(){
        commonMap = new LinkedHashMap<>();
    }

}
