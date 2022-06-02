package com.toptal.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class AppHelpers {

    Logger logger = LoggerFactory.getLogger(AppHelpers.class);
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
        logger.debug("waiting for element "+ element );
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void enterTextOnElement(WebElement webElement,String text){
        logger.debug("Text entered on "+webElement +" is"+text);
        waitForElement(webElement);
        webElement.clear();
        webElement.click();
        webElement.sendKeys(text);
        waitForJSandJQueryToLoad();
    }

}
