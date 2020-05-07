package com.mdababi.bookstore.service;

import com.mdababi.bookstore.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(long id);
    Book save(Book book);
    List<Book> blurrySearch(String title);
    void removeBook(long id);
}
