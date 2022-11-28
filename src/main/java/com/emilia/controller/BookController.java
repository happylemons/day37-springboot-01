package com.emilia.controller;

import com.emilia.pojo.Book;
import com.emilia.pojo.Code;
import com.emilia.pojo.Result;
import com.emilia.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    @GetMapping("say")
    public String hello(){
        return "hello";
    }
    @Autowired
    private BookService bookService;

    @GetMapping
    public Result getAll() {
        List<Book> books = bookService.getAll();
        Integer code = books != null ? Code.GET_OK : Code.GET_ERR;
        String msg = books != null ? "查询数据成功" : "查询数据失败";
        return new Result(books, code, msg);
    }
}
