package com.olsh4u.onevisionlibraryapp.mapper;


import com.olsh4u.onevisionlibraryapp.dto.BaseBookResponse;
import com.olsh4u.onevisionlibraryapp.dto.BookCreateRequest;
import com.olsh4u.onevisionlibraryapp.dto.BookCreatedResponse;
import com.olsh4u.onevisionlibraryapp.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BaseBookResponse toBaseResponse(Book book) {
        BaseBookResponse baseBookResponse = new BaseBookResponse();
        baseBookResponse.setId(book.getId());
        baseBookResponse.setTitle(book.getTitle());
        baseBookResponse.setAuthor(book.getAuthor());
        baseBookResponse.setDescription(book.getDescription());
        return baseBookResponse;
    }

    public BookCreatedResponse bookCreatedResponse(Book book) {
        BookCreatedResponse bookCreatedResponse = new BookCreatedResponse();
        bookCreatedResponse.setId(book.getId());
        bookCreatedResponse.setTitle(book.getTitle());
        bookCreatedResponse.setAuthor(book.getAuthor());
        bookCreatedResponse.setDescription(book.getDescription());
        return bookCreatedResponse;
    }

    public Book toEntity(BookCreateRequest request) {
        return Book.builder()
                .author(request.getAuthor())
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }
}
