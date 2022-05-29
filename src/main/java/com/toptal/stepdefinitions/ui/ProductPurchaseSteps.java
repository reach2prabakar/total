package com.toptal.stepdefinitions.ui;

import com.toptal.page.WomenPage;
import com.toptal.processor.BaseTest;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductPurchaseSteps extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ProductPurchaseSteps.class);
    WomenPage wPage = new WomenPage(driver);

    @Given("^user navigated to the (.*) menu option$")
    public void navigateToMainMenu(String tabName){
        String menuTxt = wPage.selectMainMenu(tabName).getText();
        logger.debug("home page menu text returned : "+menuTxt);
        if(menuTxt.equalsIgnoreCase(tabName)){
            wPage.selectMainMenu(tabName).click();
        }
    }
}
