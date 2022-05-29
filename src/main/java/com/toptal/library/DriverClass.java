package com.toptal.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverClass implements Driver {

    static String driverName;
    public static String resourcePath = System.getProperty("user.dir")+"/src/test/resources/";
    static {
        driverName = new PropertyReader().getProperty("browser");
        if(driverName.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
        }else if(driverName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
        }
    }

    private WebDriver driver;

    @Override
    public WebDriver createDriver() {
        WebDriver driver = null;
        if (driverName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new ChromeDriver(chromeOptions);
        }
        this.driver = driver;
        driver.manage().window().maximize();
        return driver;
    }
}
