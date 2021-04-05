package com.krab1.webapp.controllers.restcontrollers;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.entities.Book;
import com.krab1.webapp.services.BooksService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/books")
public class BookApiController {
    private final BooksService booksService;

    public BookApiController(BooksService booksService) {
        this.booksService = booksService;
    }
    
    @GetMapping("/all")
    public List<Book> getBooks(){
        return booksService.findAll();
    }
    @PostMapping(path = "/add")
    public void save(@RequestBody Book book){
        booksService.save(book);
    }
    @DeleteMapping(path = "/delete/{bookid}")
    public void deleteBookEntity(@PathVariable("bookid") Long id){
        booksService.delete(id);
    }
    @GetMapping("/search/{name}")
    public List<Book> searchBook(@PathVariable("name") String name){
        return booksService.searchBooksByLikeName(name);
    }
    @PutMapping("/update/{bookid}")
    public void updateBook(@PathVariable("bookid") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) Double price,
                           @RequestParam(required = false) LocalDate date,
                           @RequestParam(required = false) Author author){
        booksService.update(id, name,price,date, author);
    }
}
