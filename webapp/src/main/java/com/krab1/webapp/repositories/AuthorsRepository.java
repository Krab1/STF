package com.krab1.webapp.repositories;

import com.krab1.webapp.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
    @Query("FROM Author WHERE name = ?1 and surname = ?2")
    Optional<Author> findByName(String name, String surname);
}
