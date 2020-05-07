package com.mdababi.bookstore.repository;

import com.mdababi.bookstore.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRespository extends CrudRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);
}
