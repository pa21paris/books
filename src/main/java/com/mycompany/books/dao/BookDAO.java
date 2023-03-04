/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.books.dao;
import java.util.List;
import com.mycompany.books.model.Book;
import com.mycompany.books.model.Category;
/**
 *
 * @author papar
 */
public interface BookDAO {
    public List<Book> findAllBooks();
    public List<Book> findBooksByKeyword(String keyWord);
    public List<Category> findAllCategories();
    public void insert(Book book);
    public void delete(Book book);
    public void update(Book book);
}
