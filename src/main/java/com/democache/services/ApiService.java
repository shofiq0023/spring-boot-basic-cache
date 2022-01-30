package com.democache.services;

import com.democache.model.Book;
import com.democache.repository.ApiRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRepo apiRepo;
    Logger logger = LoggerFactory.getLogger(ApiService.class);

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        apiRepo.findAll().forEach(books::add);
        logger.info("[getBooks] - Getting all books from db");
        return books;
    }

    @Cacheable(cacheNames = "books", key = "#id")
    public Optional<Book> getBook(Long id) {
        logger.info("[getBook] - getting book from db");
        return apiRepo.findById(id);
    }

    public void addBook(Book book) {
        logger.info("[addBook] - Adding book to db");
        apiRepo.save(book);
    }

    @CacheEvict(cacheNames = "books", key = "#id")
    public void deleteBook(Long id) {
        logger.info("[deleteBook] - Deleting book from db");
        apiRepo.deleteById(id);
    }

    @CachePut(cacheNames = "books", key = "#id")
    public Book updateBook(Long id, Book book) {
        logger.info("[updateBook] - Updating book in db");
        if(apiRepo.existsById(id)) {
            apiRepo.save(book);
            return book;
        } else {
            return null;
        }
    }
}
