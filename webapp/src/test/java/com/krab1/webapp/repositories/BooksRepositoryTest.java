package com.krab1.webapp.repositories;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BooksRepositoryTest {
    @Autowired
    BooksRepository booksRepository;
    @Test
    public void testFindByNameSucceed() {
        booksRepository.findByName("Kyle");
    }
    @Test
    public void testFindByNameNotSucceed() {
        booksRepository.findByName("TestNameNotExist");
    }

    @Test
    @Disabled
    public void testFindByNameLike() {

    }

    @Test
    public void testTestFindByNameLike() {
    }
}