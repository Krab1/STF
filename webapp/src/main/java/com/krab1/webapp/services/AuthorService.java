package com.krab1.webapp.services;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.repositories.AuthorsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorsRepository authorsRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public AuthorService(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }
    public List<Author> findAll(){
        return authorsRepository.findAll();
    }

    public void save(Author author){
        findByName(author.getName(), author.getSurname())
                .ifPresentOrElse(existedAuthor -> log.info("Author already exist with id: " + existedAuthor.getId()),
                        () -> authorsRepository.save(author));
    }
    public Optional<Author> findByName(String name, String surname) {
        return authorsRepository.findByName(name,surname);
    }
    public void delete(Long id){
        authorsRepository.deleteById(id);
    }
    public Author findById(Long id) {
        return authorsRepository.findById(id).orElseThrow(() -> new IllegalStateException("Author not found with id: " + id));
    }
}
