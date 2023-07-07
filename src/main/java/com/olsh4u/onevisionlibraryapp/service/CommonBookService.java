package com.olsh4u.onevisionlibraryapp.service;


import com.olsh4u.onevisionlibraryapp.dto.BaseBookResponse;
import com.olsh4u.onevisionlibraryapp.dto.BookCreateRequest;
import com.olsh4u.onevisionlibraryapp.dto.BookCreatedResponse;
import com.olsh4u.onevisionlibraryapp.entity.Book;
import com.olsh4u.onevisionlibraryapp.exception.BookAlreadyExistsException;
import com.olsh4u.onevisionlibraryapp.mapper.BookMapper;
import com.olsh4u.onevisionlibraryapp.repository.BookRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class CommonBookService implements BookService{

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public CommonBookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    private static final Logger log = LogManager.getLogger(CommonBookService.class);


    @Override
    public BookCreatedResponse addBook(BookCreateRequest bookCreateRequest) {
         Book book = bookMapper.toEntity(bookCreateRequest);

         try {
             bookRepository.addBook(book);
             return bookMapper.bookCreatedResponse(book);
         } catch (BookAlreadyExistsException e) {
             e.printStackTrace();
             throw e;
         }
         catch (Exception e) {
             log.error("An error occurred while saving the book to the database" , e.getCause() );
             throw new RuntimeException("An error occurred while saving the book to the database" , e.getCause());
         }
    }

    @Override
    public List<BaseBookResponse> getAllBooks() {
         List<Book> books = bookRepository.getBookList();

         if(books == null) return List.of();

         return books.stream().map(bookMapper::toBaseResponse).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<BaseBookResponse>> getBooksByAuthor() {
       var books = bookRepository.getBookListByAuthor();

        if(books.isEmpty()) return Collections.emptyMap();

        return books
            .stream()
            .collect(Collectors.groupingBy(Book::getAuthor, Collectors.mapping(bookMapper::toBaseResponse, Collectors.toList())));
    }

}
