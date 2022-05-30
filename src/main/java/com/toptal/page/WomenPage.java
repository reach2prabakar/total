package com.toptal.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WomenPage extends HomePage{


    @FindBy(xpath=".//body")
    public WebElement Categories;

    @FindBy(xpath = "//span[@class='cat-name']")
    public WebElement pageTxt;

    public WomenPage(WebDriver driver) {
        super(driver);
        waitForPage();
    }
    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(pageTxt));
        assertThat("Women - page is not loaded",driver.getTitle(),equalTo("Women - My Store"));
    }

    public void clickOnFilter(String filterType,String value){
        String xPath =  getXpathFramed(filterType,value);
        Categories.findElement(By.xpath(xPath)).click();
    }



}
