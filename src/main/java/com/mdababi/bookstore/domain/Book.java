package com.mdababi.bookstore.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String language;
    private String  category;
    private int numberOfPages;
    private String format;
    private String isbn;
    private double shippingWeight;
    private double listPrice;
    private double ourPrice;
    private boolean active = true;
    @Column(columnDefinition = "text")
    private String description;
    private int inStockNumber;

    @Transient
    private MultipartFile bookImage;




}
