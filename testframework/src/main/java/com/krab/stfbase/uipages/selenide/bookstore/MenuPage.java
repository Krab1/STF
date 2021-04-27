package com.krab.stfbase.uipages.selenide.bookstore;

import com.codeborne.selenide.SelenideElement;
import com.krab.stfbase.uipages.selenide.bookstore.base.BasePage;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public abstract class MenuPage extends BasePage {
    private final SelenideElement allBooksElem= $("#allbooksnav");
    private final SelenideElement newBookElem = $("#newbooksnav");
    private final SelenideElement newAuthElem = $("#newauthornav");
    @Step("Click on All Books Menu")
    public MainPage clickOnAllBooksMenu(){
        allBooksElem.click();
        takeScreenShot();
        return new MainPage();
    }
    @Step("Click on New Book Menu")
    public NewBookPage clickOnNewBookMenu(){
        newBookElem.click();
        takeScreenShot();
        return new NewBookPage();
    }
    @Step("Click on New Author Menu")
    public NewAuthorPage clickOnNewAuthorMenu(){
        newAuthElem.click();
        takeScreenShot();
        return new NewAuthorPage();
    }
}
