package com.krab1.webapp.repositories;

import com.krab1.webapp.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
    List<Book> findByNameLike(String name);
    Page<Book> findByNameLike(String name, Pageable pageable);
}
