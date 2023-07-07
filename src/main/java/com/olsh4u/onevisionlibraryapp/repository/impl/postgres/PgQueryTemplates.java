package com.olsh4u.onevisionlibraryapp.repository.impl.postgres;

public class PgQueryTemplates {

    private PgQueryTemplates() {}
        public static final String SELECT_ALL = "select * from book order by book.title desc";
        public static final String SELECT_BY_AUTHOR = "select * from book where author=:author";
        public static final String SAVE_BOOK = "insert into book (author, title , description) VALUES (:author, :title, :description)";
}
