package com.krab1.webapp.repositories;

import com.krab1.webapp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameAndAndSurname(String name, String surname);
}
