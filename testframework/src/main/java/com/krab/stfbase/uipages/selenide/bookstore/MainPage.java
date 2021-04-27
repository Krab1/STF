package com.krab.stfbase.uipages.selenide.bookstore;

import com.codeborne.selenide.SelenideElement;
import com.krab.stfbase.uipages.selenide.bookstore.entity.BookInfo;
import io.qameta.allure.Step;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;

public class MainPage extends MenuPage{
    private final SelenideElement searchField = $x("//*[@id='searchfield']");
    private final SelenideElement searchBtn = $x("//*[@id='search form']/button");
    private final SelenideElement booksTable = $x("//*[@id='tablelistbooks']");
    private final SelenideElement tablebody = $x("//*[@id='tablelistbooks']/tbody");
    private List<BookInfo> books;

    public List<BookInfo> getBooks() {
        return books;
    }

    public MainPage(){
        books = parseBooksTable();
    }

    private List<BookInfo> parseBooksTable(){
        return booksTable.$$x("tbody/tr")
                .stream()
                .map(BookInfo::Parse)
                .collect(Collectors.toList());
    }
    @Step("Searching Books on Search Field")
    public MainPage searchBookName(String bookName){
        step("Sending to search field text: " + bookName,() -> searchField.sendKeys(bookName));
        step("Clicking on Search button", () -> searchBtn.click());
        takeScreenShot();
        return new MainPage();
    }
    public EditBookPage editBook(BookInfo book){
        $x(book.getBaseXpath() + "//a[1]").click();
        takeScreenShot();
        return new EditBookPage();
    }

    public MainPage deleteBook(BookInfo book){
        $x(book.getBaseXpath() + "//a[2]").click();
        takeScreenShot();
        return new MainPage();
    }
}
