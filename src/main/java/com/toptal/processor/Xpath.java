package com.toptal.processor;

public class Xpath {

    public static String categories ="//div[@id='left_column']//span[text()='${filterType}']/parent::*/following-sibling::*//label/a[text()='${value}']/parent::*/preceding-sibling::*//input";
    public static String productList ="(//ul[@id='product_list']/li//span[@class='availability']/span[contains(text(),'In stock')]/parent::*//preceding-sibling::h5/a)[${count}]";

    public static String productPrice = "(//ul[@id='product_list']/li//span[@class='availability']/span[contains(text(),'In stock')]/parent::*//preceding-sibling::div[@class='content_price']/span[1])[${count}]";

}
