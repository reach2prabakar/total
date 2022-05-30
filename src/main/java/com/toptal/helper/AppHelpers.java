package com.toptal.helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AppHelpers {

    private static final Logger logger = LogManager.getLogger(AppHelpers.class);
    private final WebDriver driver;

    public AppHelpers(WebDriver driver){
        this.driver = driver;
    }

    public ExpectedCondition<Boolean> jsLoad(){
        return driver -> ((JavascriptExecutor) driver)
                   .executeScript("return document.readyState").toString().equals("complete");
    }

    private ExpectedCondition<Boolean> pageLoad() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return (Boolean) ((JavascriptExecutor) driver).executeScript(
                            "return window.getAllAngularTestabilities().findIndex(x=>!x.isStable()) === -1");
                } catch (JavascriptException e) {
                    return true;
                }
            }
        };
    }

    public void waitForJSandJQueryToLoad() {
        ExpectedCondition<Boolean> jsLoad = jsLoad();
        ExpectedCondition<Boolean> pageLoad = pageLoad();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.and(pageLoad, jsLoad));
    }

    public void clickOnElement(WebElement webElement){
        waitForElement(webElement);
        webElement.click();
        waitForJSandJQueryToLoad();
    }

    public void hoerOnElement(WebElement webElement){
        waitForElement(webElement);
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    public String getTextOnElement(WebElement webElement){
        waitForElement(webElement);
        return webElement.getText();
    }
    public String getAttributeOnElement(WebElement webElement,String attribute){
        return webElement.getAttribute(attribute);
    }
    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void enterTextOnElement(WebElement webElement,String text){
        logger.info("Text entered on "+webElement +" is"+text);
        waitForElement(webElement);
        webElement.clear();
        webElement.click();
        webElement.sendKeys(text);
        waitForJSandJQueryToLoad();
    }

}
