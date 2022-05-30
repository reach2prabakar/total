package com.toptal.page;

import com.toptal.helper.AppHelpers;
import com.toptal.processor.Xpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class HomePage extends AppHelpers {

    public WebDriver driver;

    @FindAll(
            @FindBy(xpath = "//div[@id='block_top_menu']/ul/li/a")
    )
    public List<WebElement> lstLstMainMenuItem;

    @FindBy(xpath="//ul[@id='product_list']/li//span[@class='availability']/span[contains(text(),'In stock')]/parent::*//preceding-sibling::div//a/span[text()='Add to cart']")
    public WebElement addToCart;

    @FindBy(xpath="//div[@id='layer_cart']//span[@class='title']")
    public WebElement addCartTitleTxt;

    @FindBy(xpath="//div[@id='layer_cart']//span[@title='Close window']")
    public WebElement closeCartPopup;

    @FindBy(xpath="//div[@class='shopping_cart']/a")
    public WebElement addCartBtn;

    public HomePage(WebDriver driver){
        super(driver);
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
    public void mouseHoverToProduct(int itemnumber){
        WebElement element = driver.findElement(By.xpath(getXpathForProductList(itemnumber,Xpath.productList)));
        hoerOnElement(element);
    }
    public String getTextOfProduct(int itemnumber){
        WebElement element = driver.findElement(By.xpath(getXpathForProductList(itemnumber,Xpath.productList)));
        return getTextOnElement(element);
    }

    public String getPriceOfProduct(int itemnumber){
        WebElement element = driver.findElement(By.xpath(getXpathForProductList(itemnumber,Xpath.productPrice)));
        return getTextOnElement(element);
    }
    public String getXpathFramed(String value1,String value2){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("filterType", value1);
        valuesMap.put("value", value2);
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(Xpath.categories);
    }

    public String getXpathForProductList(int value,String element){
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("count", String.valueOf(value));
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(element);
    }

    public void addProductToCart(){
        clickOnElement(addToCart);
    }
    public void closeCartPopup(){
        clickOnElement(closeCartPopup);
    }
    public void openCart(){
        clickOnElement(addCartBtn);
    }
    public void validateProductAdded() {
        String successText = getTextOnElement(addCartTitleTxt).trim();
        assertThat("Product is not added to the cart",successText.equals("Product successfully added to your shopping cart"));
    }
}
