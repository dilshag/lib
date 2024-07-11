package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.BookDAO;
import lk.ijse.deb.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    public boolean delete(String isbn) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM book WHERE ISBN=?",isbn);
    }

    public boolean save(Book book) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO book VALUES(?, ?, ?, ?, ?,?)",book.getISBN(),book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId());
    }

    public boolean update(Book book) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE book SET bookName =?, category = ?, qtyOnHand = ?, rackCode = ?,authorId = ? WHERE ISBN =?",book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId(),book.getISBN());
    }

    public Book search(String isbn) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM book WHERE ISBN =?",isbn);
       rst.next();

       return new Book(rst.getString("ISBN"),rst.getString("bookName"),rst.getString("category"),rst.getString("qtyOnHand"),rst.getString("rackCode"),rst.getString("authorId"));
    }

    public List<Book> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst =SQLUtil.execute("SELECT * FROM book");
        List<Book> bookList = new ArrayList<>();

        while(rst.next()){
            Book book = new Book(rst.getString("ISBN"),rst.getString("bookName"),rst.getString("category"),rst.getString("qtyOnHand"),rst.getString("rackCode"),rst.getString("authorId"));

        bookList.add(book);
        }
        return bookList;
    }
}
/*
private String ISBN;
private String bookName;
private String category;
private String qtyOnHand;
private String rackCode;
private String authorId;

 */