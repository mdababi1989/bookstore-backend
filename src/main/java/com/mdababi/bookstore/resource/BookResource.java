package com.mdababi.bookstore.resource;

import com.mdababi.bookstore.domain.Book;
import com.mdababi.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookResource {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
     return bookService.save(book);
    }
}
