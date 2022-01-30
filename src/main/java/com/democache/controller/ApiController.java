package com.democache.controller;

import com.democache.model.Book;
import com.democache.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getBooks")
    public List<Book> getBooks() {
        return apiService.getBooks();
    }

    @GetMapping("/getBook/{id}")
    public Optional<Book> getBook(@PathVariable("id") Long id) {
        return apiService.getBook(id);
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        apiService.addBook(book);
    }

    @PutMapping("/update/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        return apiService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        apiService.deleteBook(id);
    }
}
