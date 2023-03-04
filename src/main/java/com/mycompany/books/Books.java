/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.books;
import java.util.List;
import com.mycompany.books.model.*;
import com.mycompany.books.dao.BookDaoImpl;
/**
 *
 * @author papar
 */
public class Books {
    public static void main(String[] args) {
        BookDaoImpl bdi=new BookDaoImpl();
        System.out.println("All Books");
        printBookList(bdi.findAllBooks());
        System.out.println("\nBooks by keyword");
        printBookList(bdi.findBooksByKeyword("Java"));
        System.out.println("\nAll Categories");
        printCategoryList(bdi.findAllCategories());
        
    }
    
    static private void printBookList(List<Book> l){
        for(Book b : l){
            System.out.println(b);
        }
    }
    
    static private void printCategoryList(List<Category> l){
        for(Category c : l){
            System.out.println(c);
        }
    }
}
