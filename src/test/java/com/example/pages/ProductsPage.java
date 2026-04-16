package com.example.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;

public class ProductsPage {
    private final String pageTitle = "[data-test='title']";
    private final String inventoryList = ".inventory_list";
    private final String addToCartButtons = "[data-test^='add-to-cart']";
    private final String cartIcon = ".shopping_cart_link";
    private final String cartBadge = ".shopping_cart_badge";

    public ProductsPage verifyPageLoaded (){
        $(pageTitle).shouldHave(text("Products"));
        $(inventoryList).shouldBe(visible);
        return this;
    }
    public String getPageTitle(){
        return $(pageTitle).getText();

    }
    public ProductsPage addFirstProductToCart(){
        $(addToCartButtons).click();
        return this;
    }
    public ProductsPage addProductToCartByName(String productName){
        $(String.format("[data-test='add-to-cart-%s']", productName.toLowerCase().replace(" ", "-"))).click();
        return this;
    }

    public int getCartItemCount(){
        if ($(cartBadge).isDisplayed()){
            return Integer.parseInt($(cartBadge).getText());
        }
        return 0;
    }
    public CartPage openCart(){
        $(cartIcon).click();
        return new CartPage();
    }

    public ProductsPage addProductToCart(String productName){
        String productId = productName.toLowerCase().replace(" ","-");
        String selector = "[data-test='add-to-cart-" + productId + "']";
        $(selector).click();
        return this;
    }
    public ProductsPage addFirstProduct(){
        $(addToCartButtons).click();
        return this;
    }
    public int getCartCount(){
        if ($(cartBadge).isDisplayed()){
            return Integer.parseInt($(cartBadge).getText());
        }
        return 0;
    }

}
