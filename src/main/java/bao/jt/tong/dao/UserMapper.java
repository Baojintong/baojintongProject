package bao.jt.tong.dao;

import bao.jt.tong.domain.Book;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<Book> findBookByName(Map m);

    Integer saveBook(Map m);

    Integer login(Map m);

    List<Book> query();
}
