package com.olsh4u.onevisionlibraryapp.repository.impl.postgres;


import com.olsh4u.onevisionlibraryapp.entity.Book;
import com.olsh4u.onevisionlibraryapp.exception.BookAlreadyExistsException;
import com.olsh4u.onevisionlibraryapp.exception.BookNotSavedException;
import com.olsh4u.onevisionlibraryapp.exception.handlers.GlobalExceptionHandler;
import com.olsh4u.onevisionlibraryapp.mapper.BookRowMapper;
import com.olsh4u.onevisionlibraryapp.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.olsh4u.onevisionlibraryapp.mapper.BookRowMapper.*;
import static com.olsh4u.onevisionlibraryapp.repository.impl.postgres.PgQueryTemplates.*;


@Repository
@Primary
public class PostgresBookRepository implements BookRepository {

    private final BookRowMapper bookRowmapper;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostgresBookRepository(BookRowMapper bookRowmapper, NamedParameterJdbcTemplate jdbcTemplate) {
        this.bookRowmapper = bookRowmapper;
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    @Override
    public List<Book> getBookList() {
        try {
            return jdbcTemplate.query(SELECT_ALL, bookRowmapper);
        } catch (Exception e) {
            log.error("Error while getting books from database. ErrorMessage: {} , Cause: {}", e, e.getMessage(),e.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public Book addBook(Book book) {
        if(bookExists(book)) throw new BookAlreadyExistsException("Book with title: " + book.getTitle() + " and author: " + book.getAuthor() + " already exists");

        var params = new MapSqlParameterSource()
            .addValue(AUTHOR, book.getAuthor())
            .addValue(TITLE, book.getTitle())
            .addValue(DESCRIPTION, book.getDescription());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(SAVE_BOOK, params, keyHolder);
            log.info("Book saved successfully. Author: {} , Title: {}" , book.getAuthor(), book.getTitle());
        } catch (Exception e) {
            log.error("Error saving book. With title: {} , Author: {} \n. Cause {}", book.getTitle(), book.getAuthor() , e.getCause());
            throw new BookNotSavedException("Error while trying to save book message: " + e.getMessage() , e.getCause());
        }
        return book;
    }

    @Override
    public List<Book> getBookListByAuthor() {
        try {
            return jdbcTemplate.query(
                SELECT_ALL,
                bookRowmapper);
        } catch (Exception e) {
            log.error("getBookListByAuthor() thrown exception while processing request: " + e.getMessage(), e.getCause());
            return Collections.emptyList();
        }
    }
    private boolean bookExists(Book book) {
        Integer result =  jdbcTemplate.queryForObject("SELECT count(*) FROM book WHERE author = :author AND title = :title" ,
            new MapSqlParameterSource().addValue("author", book.getAuthor()).addValue("title", book.getTitle()), Integer.class);
        return result != null && result != 0;
    }


}
