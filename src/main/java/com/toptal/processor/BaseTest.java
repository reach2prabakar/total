package com.toptal.processor;

import com.toptal.library.DriverManager;
import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;

public class BaseTest {

    public WebDriver driver;
    public PropertyReader propertyReader;

    public static LinkedHashMap<String,String> commonMap;
    public static LinkedHashMap<String,String> apiMap;

    public BaseTest(){
        driver = DriverManager.getDriver();
        propertyReader = new PropertyReader();
    }

    public void initUiMap(){
        commonMap = new LinkedHashMap<>();
    }
    public void initAPIMap(){
        apiMap = new LinkedHashMap<>();
    }
}
