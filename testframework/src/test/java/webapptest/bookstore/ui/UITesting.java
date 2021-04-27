package webapptest.bookstore.ui;

import com.codeborne.selenide.Selenide;
import com.krab.stfbase.listeners.AllureScreenshotsListener;
import com.krab.stfbase.uipages.selenide.bookstore.MainPage;
import com.krab.stfbase.uipages.selenide.bookstore.entity.BookInfo;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import webapptest.bookstore.baseclass.UITestBoot;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Listeners(AllureScreenshotsListener.class)
public class UITesting extends UITestBoot {
    @BeforeMethod
    private void beforeTest(){
        Selenide.open("http://localhost:8080");
    }

    @Test(description = "Checking if table parsed")
    public void checkIfTableIsNullTest() {
        MainPage mainPage = new MainPage();
        List<BookInfo> books = mainPage.getBooks();
        assertThat(books)
                .as("Checking if Tables is empty or null")
                .isNotNull()
                .isNotEmpty();
    }

    @Test(description = "Checking search function",dependsOnMethods = "deletingBookTest")
    public void checkSearchButtonTest() {
        MainPage mainPage = new MainPage();
        List<BookInfo> books = mainPage.getBooks();
        if (books.isEmpty()){
            throw new RuntimeException("Books not found\\parsed from page");
        }
        books.stream().findAny().ifPresent(book -> {
            MainPage mainPage1 = mainPage.searchBookName(book.getName());
            assertThat(mainPage1.getBooks()).as("Searching any books")
                    .isNotNull()
                    .isNotEmpty()
                    .hasSize(1)
                    .extracting(BookInfo::getName)
                    .containsAnyOf(book.getName());
        });
    }

    @Test(description = "Checking delete function")
    public void deletingBookTest() {
        MainPage mainPage = new MainPage();
        List<BookInfo> books = mainPage.getBooks();
        if (books.isEmpty()){
            throw new RuntimeException("Books not found\\parsed from page");
        }
        books.stream().findAny().ifPresent(book -> {
            MainPage mainPage1 = mainPage.deleteBook(book);
            assertThat(mainPage1.getBooks()).as("Checking if books still on the page")
                    .isNotEmpty()
                    .extracting(BookInfo::getName)
                    .doesNotContain(book.getName());
        });
    }
}
