package com.emilia.service;

import com.emilia.pojo.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();
    void add();
}
