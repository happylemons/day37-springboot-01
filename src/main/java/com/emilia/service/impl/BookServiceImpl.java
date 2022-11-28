package com.emilia.service.impl;

import com.emilia.dao.BookDao;
import com.emilia.pojo.Book;
import com.emilia.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> getAll() {
        List<Book> books = bookDao.getAll();
        return books;
    }

    @Override
    public void add() {
        System.out.println("add book service success");
    }
}
