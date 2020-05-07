package com.mdababi.bookstore.resource;

import com.mdababi.bookstore.domain.Book;
import com.mdababi.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookResource {
    BookService bookService;

    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("/add/image")
    public ResponseEntity upload(@RequestParam("id") Long id,
                                 HttpServletResponse response,
                                 HttpServletRequest request) {
        try {
            Book book = bookService.findById(id);
            MultipartHttpServletRequest multipartRequest =(MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";
            byte[] bytes = multipartFile.getBytes();
            File file = new File("F:/storage/images/book/"+fileName);
            BufferedOutputStream stream = new BufferedOutputStream((new FileOutputStream(file)));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Upload Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public List<Book> bookList() {
        return this.bookService.findAll();
    }


}
