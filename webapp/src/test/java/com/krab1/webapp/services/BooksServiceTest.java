package com.krab1.webapp.services;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.entities.Book;
import com.krab1.webapp.repositories.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {
    @Mock
    BooksRepository booksRepository;
    BooksService booksService;

    @BeforeEach
    void setup(){
        booksService = new BooksService(booksRepository);
    }

    @Test
    public void testSave() {
        Book book = new Book("123", 111.123, LocalDate.now().minusDays(5), new Author("Test", "Testingoff"));
        booksService.save(book);
        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(booksRepository).save(bookArgumentCaptor.capture());
        Book value = bookArgumentCaptor.getValue();
        assertThat(value).isEqualTo(book);
    }

    @Test
    @Disabled
    public void testSearchBooksByLikeName() {
    }

    @Test
    @Disabled
    public void testSearchBookWithMatcher() {
    }

    @Test
    @Disabled
    public void testUpdate() {
    }

    @Test
    public void testFindById() {
        booksService.findById(3L);
        verify(booksRepository).findById(3L);
    }
}