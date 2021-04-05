package com.krab1.webapp.boot;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.entities.Book;
import com.krab1.webapp.repositories.AuthorsRepository;
import com.krab1.webapp.repositories.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class Bootstrap implements CommandLineRunner {
    private final AuthorsRepository authorsRepository;
    private final BooksRepository booksRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public Bootstrap(AuthorsRepository authorsRepository, BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Kartman");
        Author phil = new Author("Phill", "Spencer");
        Book mom = new Book("Kyle's mom", 100.0, LocalDate.now().minusYears(10), eric);
        authorsRepository.save(eric);
        authorsRepository.save(phil);
        List<Book> collect = Stream.generate(Random::new).limit(50).map(e -> new Book("book" + e.nextInt(5000), e.nextDouble(), LocalDate.now().minusMonths(e.nextInt(100)), e.nextInt(100) < 50 ? eric : phil)).collect(Collectors.toList());
        booksRepository.save(mom);
        booksRepository.saveAll(collect);
        logger.info("authors count: " + authorsRepository.count());
        logger.info("books count: " + booksRepository.count());
    }
}
