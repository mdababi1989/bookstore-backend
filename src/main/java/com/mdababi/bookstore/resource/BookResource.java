package com.mdababi.bookstore.resource;

import com.mdababi.bookstore.domain.Book;
import com.mdababi.bookstore.service.BookService;
import com.mdababi.bookstore.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookResource {
    BookService bookService;
    @Autowired
    private FileStorageService fileStorageService;

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
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";
            fileStorageService.storeFile(multipartFile, fileName);
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

    @GetMapping("{id}")
    public Book getbook(@PathVariable("id") Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/get/image")
    public ResponseEntity<Resource> getbookImage(@RequestParam("id") Long id, HttpServletRequest request) {
        String fileName = id + ".png";
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}



/*
    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
*/
