package com.toptal.stepdefinitions.ui;

import com.toptal.page.CheckOutPage;
import com.toptal.page.HomePage;
import com.toptal.page.WomenPage;
import com.toptal.processor.BaseTest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ProductPurchaseSteps extends BaseTest {

    private static final Logger logger = LogManager.getLogger(ProductPurchaseSteps.class);
    HomePage hPage = new HomePage(driver);
    WomenPage wPage;


    @Given("^user navigated to the (.*) menu option$")
    public void navigateToMainMenu(String tabName) {
        String menuTxt = hPage.selectMainMenu(tabName).getText();
        logger.debug("home page menu text returned : " + menuTxt);
        if (menuTxt.equalsIgnoreCase(tabName)) {
            hPage.selectMainMenu(tabName).click();
        }
        wPage = new WomenPage(driver);
    }

    @When("^user search for the product with following search criteria")
    public void searchProductWithCriteria(DataTable data) {
        List<Map<String, String>> listData = data.asMaps();
        for (Map<String, String> maplist : listData) {
            for (String mapData : maplist.keySet()) {
                wPage.clickOnFilter(mapData, maplist.get(mapData));
                commonMap.put(mapData, maplist.get(mapData));
            }
        }
    }
    @And("^user adds the number of product to the cart on hover")
    public void addProductToCartOnHover(DataTable data){
        List<String> lst = data.asList();
        int totalItemToAdd = Integer.parseInt(lst.get(0));
        String productText,productPrice;
        for (int i=1;i<=totalItemToAdd;i++){
            productPrice = wPage.getPriceOfProduct(i);
            wPage.mouseHoverToProduct(i);
            productText = wPage.getTextOfProduct(i);
            commonMap.put("productText"+i,productText);
            commonMap.put("productPrice"+i,productPrice);
            wPage.addProductToCart();
            wPage.validateProductAdded();
            wPage.closeCartPopup();
        }
    }

    @Then("^user views the cart and does the checkout")
    public void doCheckout(){
        wPage.openCart();
        CheckOutPage cPage = new CheckOutPage(driver);
        cPage.validateProductDesc(commonMap);
        cPage.proceedToPayment();
    }
}
