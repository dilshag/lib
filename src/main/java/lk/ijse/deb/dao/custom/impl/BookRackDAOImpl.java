package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.BookRackDAO;
import lk.ijse.deb.entity.BookRack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRackDAOImpl implements BookRackDAO {
    public boolean delete(String rackCode) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM bookRack WHERE rackCode  = ?",rackCode);
    }

    public boolean save(BookRack bookRack) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO bookRack VALUES(?, ?, ?, ?)",bookRack.getRackCode(),bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks());
    }

    public boolean update(BookRack bookRack) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE bookRack SET qtyBooks = ?, categoryOfBooks  = ? , nameOfBooks = ? WHERE rackCode = ?",bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks(),bookRack.getRackCode());
    }

    public BookRack search(String rackCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM bookRack WHERE rackCode = ?",rackCode);
        rst.next();
        return new BookRack(rst.getString("rackCode"),rst.getString("qtyBooks"),rst.getString("categoryOfBooks"),rst.getString("nameOfBooks"));
    }

    public List<BookRack> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM bookRack");
        List<BookRack> bookRackList = new ArrayList<>();

        while (rst.next()){
            BookRack bookRack = new BookRack(rst.getString("rackCode"),rst.getString("qtyBooks"),rst.getString("categoryOfBooks"),rst.getString("nameOfBooks"));

            bookRackList.add(bookRack);
        }
        return bookRackList;
    }
    public List<String> getRackCode() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT rackCode    FROM  bookRack");
        List<String> codeList = new ArrayList<>();

        while(rst.next()){
            codeList.add(rst.getString(1));
        }
        return codeList;
    }
    }

