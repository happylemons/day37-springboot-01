package com.emilia.dao;

import com.emilia.pojo.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Mapper  //建议在Application里扫包
public interface BookDao {
    @Select("select * from tbl_book")
    List<Book> getAll();
}
