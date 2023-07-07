package com.olsh4u.onevisionlibraryapp.repository;


import com.olsh4u.onevisionlibraryapp.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookRepository {


    Book addBook(Book book);

    List<Book> getBookList();

    List<Book> getBookListByAuthor();

}
