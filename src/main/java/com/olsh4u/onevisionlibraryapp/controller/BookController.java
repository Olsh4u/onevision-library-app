package com.olsh4u.onevisionlibraryapp.controller;

import com.olsh4u.onevisionlibraryapp.dto.*;
import com.olsh4u.onevisionlibraryapp.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import static com.olsh4u.onevisionlibraryapp.dto.ResponseData.response;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<ResponseData<BookCreatedResponse>> addBook(@RequestBody @Valid BookCreateRequest bookCreateRequest) {
        return response(bookService.addBook(bookCreateRequest), HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public ResponseEntity<ResponseData<List<BaseBookResponse>>> getAllBooks() {
        return response(bookService.getAllBooks() , HttpStatus.OK);
    }

    @GetMapping("/authors")
    public ResponseEntity<ResponseData<Map<String, List<BaseBookResponse>>>> getBooksByAuthor() {
        return response(bookService.getBooksByAuthor(), HttpStatus.OK);
    }

}
