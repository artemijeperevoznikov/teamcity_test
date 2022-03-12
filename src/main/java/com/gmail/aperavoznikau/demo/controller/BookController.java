package com.gmail.aperavoznikau.demo.controller;

import com.gmail.aperavoznikau.demo.service.BookService;
import com.gmail.aperavoznikau.demo.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/api/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List getBooks() {
        return bookService.findAll();
    }

}
