/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.books.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.books.model.*;
/**
 *
 * @author papar
 */
public class BookDaoImpl implements BookDAO{
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
        }
    }
    private Connection openConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/books","Char",".St10cla07");
        } catch (Exception e) {
            return null;
        }
    }
    private void closeConnection(Connection con){
        if(con!=null){
            try {
                con.close();
            } catch (Exception e) {
            }
        }
    }

    private List<Book> chargeListOfBooksFromResultSet(ResultSet rs, Connection con){
        List<Book> res=new ArrayList<>();
        try {
            Statement stm=con.createStatement();
            while(rs.next()){
                List<Author> authorList=new ArrayList<>();
                Book b=new Book();
                b.setId(rs.getLong(1));
                b.setCategoryId(rs.getLong(2));
                b.setBookTitle(rs.getString(3));
                b.setPublisherName(rs.getString(4));
                ResultSet rsAuthors=stm.executeQuery("SELECT * FROM Author where BOOK_ID="+b.getId());
                while(rsAuthors.next()){
                    Author a=new Author();
                    a.setBookId(b.getId());
                    a.setFirstName(rsAuthors.getString(3));
                    a.setLastName(rsAuthors.getString(4));
                    a.setId(rsAuthors.getLong(1));
                    authorList.add(a);
                }            
                b.setAuthors(authorList);
                res.add(b);
            }            
        } catch (Exception e) {
        }
        return res;
    }
    
    @Override
    public List<Book> findAllBooks() {
        List<Book> res=new ArrayList<>();
        Connection con=null;
        
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            stm.executeQuery("SELECT * FROM Book");
            ResultSet rs=stm.getResultSet();
            res=chargeListOfBooksFromResultSet(rs, con);
            
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
        return res;
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> res=new ArrayList<>();
        Connection con=null;
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            ResultSet rs=stm.executeQuery("SELECT * FROM category");
            while(rs.next()){
                Category c=new Category();
                c.setId(rs.getLong(1));
                c.setCategoryDescription(rs.getString(2));
                res.add(c);
            }
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
        return res;
    }

    @Override
    public List<Book> findBooksByKeyword(String keyWord) {
        List<Book> res=new ArrayList<>();
        Connection con=null;
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            String sqlQuerry="Select Book.ID, CATEGORY_ID, BOOK_TITLE, PUBLISHER from Book "
                    + "inner join Author on Book.ID=Author.BOOK_ID "
                    + "where BOOK_TITLE LIKE '%"+keyWord.trim()+"%' or "
                    + "FIRST_NAME LIKE '%"+keyWord.trim()+"%' OR "
                    + "LAST_NAME LIKE '%"+keyWord.trim()+"%';";
            ResultSet rs=stm.executeQuery(sqlQuerry);
            res=chargeListOfBooksFromResultSet(rs, con);
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
        return res;
    }

    @Override
    public void delete(Book book) {
        Connection con=null;
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            stm.executeUpdate("DELETE FROM Book where ID="+book.getId()+";");
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void insert(Book book) {
        Connection con=null;
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            stm.executeUpdate("INSERT INTO Books VALUES ("+book.getId()+","+book.getCategoryId()+""
                    + ","+book.getBookTitle()+","+book.getPublisherName()+");");
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
    }

    @Override
    public void update(Book book) {
        Connection con=null;
        try {
            con=openConnection();
            Statement stm=con.createStatement();
            stm.executeUpdate("UPDATE Book SET ID="+book.getId()+", CATEGORY_ID="+book.getCategoryId()+""
                    + ",BOOK_TITLE="+book.getBookTitle()+",PUBLISHER="+book.getPublisherName()+""
                            + "WHERE ID="+book.getId()+";");
        } catch (Exception e) {
        } finally {
            closeConnection(con);
        }
    }
    
    
    
}
