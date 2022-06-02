package com.toptal.page;

import com.toptal.library.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckOutPage extends HomePage {

    Logger logger = LoggerFactory.getLogger(CheckOutPage.class);
    @FindBy(xpath = "//table[@id='cart_summary']")
    public WebElement cartTable;

    @FindAll(
            @FindBy(xpath = "//table[@id='cart_summary']/tbody//td[@class='cart_description']//p/a")
    )
    public List<WebElement> cartDescriptionTxt;

    @FindAll(
            @FindBy(xpath = "//table[@id='cart_summary']/tbody//td[@class='cart_description']//small/a")
    )
    public List<WebElement> cartSizeTxt;

    @FindAll(
            @FindBy(xpath = "//table[@id='cart_summary']/tbody//td[@class='cart_total']/span")
    )
    public List<WebElement> cartTotalTxt;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']//span[contains(text(),'Proceed to checkout')]")
    public WebElement checkoutBtn;

    @FindBy(xpath = "//div[@class='checker']//input")
    public WebElement termsCheckBox;

    @FindBy(xpath = "//a[@class='bankwire']")
    public WebElement payByWireLnk;

    @FindBy(xpath = "//p[@class='cart_navigation clearfix']//span[contains(text(),'I confirm my order')]")
    public WebElement confirmOrderBtn;

    public CheckOutPage(WebDriver driver) {
        super(driver);
        waitForPage();
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(cartTable));
        assertThat("Checkout - page is not loaded", driver.getTitle().trim(), equalTo("Order - My Store"));
    }

    public void validateProductDesc(LinkedHashMap<String, String> cMapData) {

        int i=1;
        for (WebElement webElement : cartDescriptionTxt) {
            String cartDesc = webElement.getText();
            String addedProduct = cMapData.get("productText"+i);
            logger.debug("Asserting -> : cartDescription " + cartDesc + "added items to cart :"+addedProduct);
            assertThat("the added product is different from the product in the cart", addedProduct.equals(cartDesc));
        }
        for(WebElement webElement : cartSizeTxt){
            String sizeText = webElement.getText();
            String size = sizeText.split(",")[0].split(":")[1];
            String addedSize = cMapData.get("Size"+i);
            logger.debug("Asserting -> : cartsize " + size + "added items to cart :"+addedSize);
            assertThat("the added size is different from the product in the cart", addedSize.equals(size));
        }
        for (WebElement webElement : cartTotalTxt) {
            String cartTotal = webElement.getText();
            String addedProductTotal = cMapData.get("productPrice" + i);
            logger.debug("Asserting -> : carttotal " + cartTotal + "added items to cart :"+addedProductTotal);
            assertThat("the added product total is different from the product total in the cart", addedProductTotal.equals(cartTotal));
        }
    }

    public void proceedToPayment(){
        clickOnElement(checkoutBtn);
        clickOnElement(checkoutBtn);
        if(getAttributeOnElement(termsCheckBox,"class")!="checked"){
            termsCheckBox.click();
        }
        clickOnElement(checkoutBtn);
        clickOnElement(payByWireLnk);
        clickOnElement(confirmOrderBtn);
    }
}
