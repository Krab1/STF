package com.krab1.webapp.services;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.entities.Book;
import com.krab1.webapp.repositories.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Page<Book> findAll(int pagenumber){
        PageRequest req = PageRequest.of(pagenumber - 1, 10);
        return booksRepository.findAll(req);
    }

    public void update(Book book){
        booksRepository.save(book);
    }

    public Page<Book> findByNameLike(String name, int pagenumber){
        PageRequest req = PageRequest.of(pagenumber - 1, 10);
        return booksRepository.findByNameLike(name, req);
    }

    public void save(Book book){
        findByName(book.getName())
                .ifPresentOrElse(existedBook -> log.info("Book already exist with id: " + existedBook.getId()),
                        () -> booksRepository.save(book));
    }
    public void delete(Book book){
        booksRepository.delete(book);
    }
    public void delete(Long id){
        booksRepository.deleteById(id);
    }
    public Optional<Book> findByName(String name){
        return booksRepository.findByName(name);
    }
    public List<Book> searchBooksByLikeName(String name){
        return booksRepository.findByNameLike(name);
    }
    /**
     Just for testing how matcher works
     */
    public List<Book> searchBookWithMatcher(String name){
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Book> example = Example.of(new Book(name, null, null,  null),exampleMatcher);
        return booksRepository.findAll(example);
    }
    @Transactional
    public void update(Long id, String name, Double price, LocalDate date, Author author) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    if (!StringUtils.isEmptyOrWhitespace(name)) {
                        Optional<Book> bookByName = booksRepository.findByName(name);
                        if (bookByName.isEmpty()){
                            book.setName(name);
                        }else {
                            throw new IllegalStateException("Book with that name already exist");
                        }
                    }
                    if(price != null && price > 0) {
                        book.setPrice(price);
                    }
                    if(date != null){
                        if(date.isBefore(LocalDate.now())) {
                            book.setDateofissue(date);
                        }
                        else {
                            throw new IllegalStateException("Date can not be larger than current date");
                        }
                    }
                    if (author != null){
                        book.setAuthor(author);
                    }else {
                        throw new IllegalStateException("Author is not setted up");
                    }
                }
        );
    }
    public Book findById(Long id) {
        Optional<Book> optBook = booksRepository.findById(id);
        return optBook.orElseThrow(() -> new IllegalStateException("Book not found with id: " + id));
    }
}
