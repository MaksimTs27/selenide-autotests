package com.example.pages;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final String usernameInput = "#user-name";
    private final String passwordInput = "#password";
    private final String loginButton = "#login-button";
    private final String errorMessage = "[data-test='error']";

    public LoginPage openPage(){
        open("/");
        return this;
    }

    public LoginPage enterUserName (String userName){
        $(usernameInput).setValue(userName);
        return this;
    }
    public LoginPage enterUserPassword (String userPassword){
        $(passwordInput).setValue(userPassword);
        return this;
    }
    public ProductsPage clickLoginButtom(){
        $(loginButton).click();
        return new ProductsPage();
    }
    public LoginPage clickLoginButtomButExpectErrorMessage(){
        $(loginButton).click();
        return this;
    }
    public String getErrorMessage(){
        return $(errorMessage).shouldBe(visible).getText();
    }
    public boolean isMessageVisible(){
        return $(errorMessage).isDisplayed();
    }
}
