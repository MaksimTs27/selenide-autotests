package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private final String cartItems = ".cart_item";

    private  final String cartitemName = ".inventory_item_name";
    private final String checkoutButton = "[data-test='checkout']";
    private final String continueShoppingButton = "[data-test='continue-shopping']";
    private final String removeButtons = "[data-test^='remove']";
    private final String cartBadge = ".shopping_cart_badge";

    public CartPage verifyPageLoaded(){
        $(cartItems).shouldBe(visible);
        return this;
    }

    public int getItemsCount(){
        return  $$(cartItems).size();
    }

    public boolean isProductInCart(String productName){
        return $(cartitemName).getText().contains(productName);
    }
    public CartPage removeFirstItem(){
        $(removeButtons).click();
        return this;
    }
//    public CheckoutPage clickCheckout(){
//        $(checkoutButton).click();
//        return new CheckoutPage();
//    }
    public ProductsPage clickContinueShopping(){
        $(continueShoppingButton).click();
        return new ProductsPage();
    }
    public boolean isCartEmpty(){
        return !$(cartBadge).isDisplayed();
    }
}
