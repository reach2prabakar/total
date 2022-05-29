package com.toptal.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    public WebDriver driver;

    @FindAll(
            @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a")
    )
    public List<WebElement> lstLstMainMenuItem;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement selectMainMenu(String mainMenuName){
        if(selectMainMenuispresent(mainMenuName)){
            return lstLstMainMenuItem
                    .stream()
                    .filter(items -> items.getText().equalsIgnoreCase(mainMenuName.toLowerCase()))
                    .findFirst().get();
        }else{
            throw new RuntimeException("Element is not present");
        }

    }
    public boolean selectMainMenuispresent(String mainMenuName){
         return lstLstMainMenuItem
                .stream()
                .filter(items -> items.getText().equalsIgnoreCase(mainMenuName.toLowerCase()))
                .findFirst().isPresent();
    }
}
