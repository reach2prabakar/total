package com.toptal.page;

import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LandingPage extends HomePage {

    public LandingPage(WebDriver driver){super(driver);}

    public void loadUrl(){
        driver.get(new PropertyReader().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        assertThat("automation practice home page is not loaded",driver.getTitle(),
                equalTo("My Store"));
    }



}
