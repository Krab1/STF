package com.krab1.webapp.controllers;

import com.krab1.webapp.entities.Author;
import com.krab1.webapp.entities.Book;
import com.krab1.webapp.services.AuthorService;
import com.krab1.webapp.services.BooksService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
public class BookStoreUIController {
    private final BooksService booksService;
    private final AuthorService authorService;

    public BookStoreUIController(BooksService booksService, AuthorService authorService) {
        this.booksService = booksService;
        this.authorService = authorService;
    }

    @RequestMapping("/")
    public String getBooks(Model model,  @ModelAttribute("name") String name){
        return getPage(1, model, name);
    }

    @GetMapping("/page/{pagenumber}")
    public String getPage(@PathVariable("pagenumber") int currentpage, Model model, String name){
        Page<Book> page = StringUtils.isEmptyOrWhitespace(name) ? booksService.findAll(currentpage) : booksService.findByNameLike("%" + name + "%", 1);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Book> content = page.getContent();
        model.addAttribute("books", content);
        model.addAttribute("totalitems", totalItems);
        model.addAttribute("totalpages", totalPages);
        model.addAttribute("currentpage", currentpage);
        return "books/list";
    }

    @RequestMapping("/newbook")
    public String showAddNewBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/newbook";
    }

    @RequestMapping(value = "/savebook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book){
        booksService.save(book);
        return "redirect:/";
    }
    @RequestMapping(value = "/updatebook", method = RequestMethod.POST)
    public String updateBook(@ModelAttribute("book") Book book){
        booksService.update(book);
        return "redirect:/";
    }
    @RequestMapping(value = "/bookdelete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id){
        booksService.delete(id);
        return "redirect:/";
    }
    @ModelAttribute("dateFormat")
    public String dateFormat() {
        return new SimpleFormatter().toString();
    }

    @ModelAttribute("authors")
    public List<Author> authorsList(){
        return authorService.findAll();
    }

    @RequestMapping(value = "/bookedit/{id}")
    public ModelAndView showEditBookForm(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("books/editbook");
        Book book = booksService.findById(id);
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @RequestMapping("/newauthor")
    public String showAddNewAuthorForm(Model model){
        Author author = new Author();
        model.addAttribute("author", author);
        return "books/newauthor";
    }

    @RequestMapping(value = "/saveauthor", method = RequestMethod.POST)
    public String save(@ModelAttribute("author") Author author){
        authorService.save(author);
        return "redirect:/";
    }

}
