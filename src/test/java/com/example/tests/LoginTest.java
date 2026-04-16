package com.example.tests;

import com.example.base.BaseTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.pages.CartPage;
import com.example.pages.LoginPage;
import com.example.pages.ProductsPage;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest  extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);

    @Test
    void succesfulLoginWithStandardUser(){
      LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage
                .openPage()
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickLoginButtom();

        productsPage.verifyPageLoaded();
        assertThat(productsPage.getPageTitle()).isEqualTo("Products");
        System.out.println("Логин Успешен");

    }
    @Test
    void loginWithLockedOutUserShouldShowError(){
        LoginPage loginPage = new LoginPage();
        loginPage
                .openPage()
                .enterUserName("locked_out_user")
                .enterUserPassword("secret_sauce")
                .clickLoginButtomButExpectErrorMessage();

        assertThat(loginPage.getErrorMessage()).contains("Sorry, this user has been locked out");

        System.out.println("Ошибка отображается корректно");
    }
    @Test
    void loginWithEmptyCredentials(){
    LoginPage loginPage = new LoginPage();

    loginPage
            .openPage()
            .clickLoginButtomButExpectErrorMessage();
    assertThat(loginPage.getErrorMessage()).contains("Username is required");

        System.out.println("Пустые поля обработаны ");
    }
    @Test
    @DisplayName("Добавление товара в корзину")
    void addProductToCart(){
        ProductsPage productsPage = new LoginPage()
                .openPage()
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickLoginButtom();
        productsPage.addProductToCart("Sauce Labs Backpack");
        assertThat(productsPage.getCartCount()).isEqualTo(1);
        System.out.println("Тест прошел , товар добавлен ");
    }
    @Test
    @DisplayName("Переход в корзину")
    void openCartAndVerifyProductTest(){
        ProductsPage productsPage = new LoginPage()
                .openPage()
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickLoginButtom();

        productsPage.addProductToCart("Sauce Labs Backpack");

        CartPage cartPage = productsPage.openCart();
        cartPage.verifyPageLoaded();
        assertThat(cartPage.getItemsCount()).isEqualTo(1);
        assertThat(cartPage.isProductInCart("Sauce Labs Backpack")).isTrue();

        System.out.println(" Тест прошел: товар удален из корзины");
    }

    @Test
    void removeProductFromCart(){
        ProductsPage productsPage = new LoginPage()
                .openPage()
                .enterUserName("standard_user")
                .enterUserPassword("secret_sauce")
                .clickLoginButtom();
        productsPage.addProductToCart("Sauce Labs Backpack");
        assertThat(productsPage.getCartCount()).isEqualTo(1);

        CartPage cartPage = productsPage.openCart();
        cartPage.removeFirstItem();

        assertThat(cartPage.isCartEmpty()).isTrue();
    }
}
