package com.democache.repository;

import com.democache.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface ApiRepo extends CrudRepository<Book, Long> {
}
