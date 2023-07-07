package com.olsh4u.onevisionlibraryapp.service;

import com.olsh4u.onevisionlibraryapp.dto.BaseBookResponse;
import com.olsh4u.onevisionlibraryapp.dto.BookCreateRequest;
import com.olsh4u.onevisionlibraryapp.dto.BookCreatedResponse;

import java.util.List;
import java.util.Map;

public interface BookService {

    BookCreatedResponse addBook(BookCreateRequest bookCreateRequest);


    List<BaseBookResponse> getAllBooks();

    Map<String, List<BaseBookResponse>> getBooksByAuthor();

}
