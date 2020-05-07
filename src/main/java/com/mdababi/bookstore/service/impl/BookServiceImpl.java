package com.mdababi.bookstore.service.impl;

import com.mdababi.bookstore.domain.Book;
import com.mdababi.bookstore.repository.BookRespository;
import com.mdababi.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRespository bookRespository;

    @Override
    public List<Book> findAll() {
        List<Book> activeBooksList = new ArrayList<>();
        bookRespository.findAll().forEach(book -> {
            if(book.isActive())
                activeBooksList.add(book);
        });
        return activeBooksList;
    }

    @Override
    public Book findById(long id) {
        return bookRespository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        return bookRespository.save(book);
    }

    @Override
    public List<Book> blurrySearch(String keyword) {
        List<Book> activeBooksList = new ArrayList<>();
        bookRespository.findByTitleContaining(keyword).stream().filter(book -> book.isActive()).forEach(activeBooksList::add);
        return activeBooksList;
    }

    @Override
    public void removeBook(long id) {
        bookRespository.deleteById(id);
    }
}
