package com.krab1.webapp.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class AuthorsRepositoryTest {
    @Autowired
    AuthorsRepository authorsRepository;
    @Test
    public void testFindByNameAndAndSurname() {
    }
}