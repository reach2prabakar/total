package com.toptal.page;

import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LandingPage extends HomePage {

    @FindBy(xpath="//a[@title='Log in to your customer account']")
    public WebElement signInlnk;

    @FindBy(xpath="//input[@id='email']")
    public WebElement eMailinpt;

    @FindBy(xpath="//input[@id='passwd']")
    public WebElement passwordinpt;

    @FindBy(xpath="//button[@id='SubmitLogin']")
    public WebElement loginBtn;

    public LandingPage(WebDriver driver){super(driver);}

    public void loadUrl(){
        driver.get(new PropertyReader().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        assertThat("automation practice home page is not loaded",driver.getTitle(),
                equalTo("My Store"));
    }

    public void signIn(){
        clickOnElement(signInlnk);
        String userName = System.getProperty("userName") != null ? System.getProperty("userName") : "no userName";
        if(userName.equals("no userName")){
            throw new RuntimeException("userName must be provided in commandline to login to application");
        }
        String passWord = System.getProperty("passWord") != null ? System.getProperty("passWord") : "no passWord";
        if(passWord.equals("no passWord")){
            throw new RuntimeException("passWord must be provided in commandline to login to application");
        }
        enterTextOnElement(eMailinpt,userName);
        enterTextOnElement(passwordinpt,passWord);
        clickOnElement(loginBtn);
    }



}
