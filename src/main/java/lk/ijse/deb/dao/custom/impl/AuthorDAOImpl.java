package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.AuthorDAO;
import lk.ijse.deb.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM author WHERE authorId  = ?",id);
    }

    public boolean save(Author author) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("INSERT INTO author VALUES(?, ?, ?, ?, ?)",author.getAuthorId(),author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty());
    }

    public boolean update(Author author) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE author SET authorName   = ?, text = ?, nationality   = ?, currentlyBooksWrittenQty = ? WHERE authorId = ?",author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty(),author.getAuthorId());
    }

    public Author search(String authorId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM author WHERE authorId  = ?",authorId);
  rst.next();
  return  new Author(rst.getString("authorId"),rst.getString("authorName"),rst.getString("text"),rst.getString("nationality"),rst.getInt("currentlyBooksWrittenQty"));
    }

    public List<Author> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM author");
       List<Author> authorList = new ArrayList<>();

       while(rst.next()){
           Author author = new Author(
                   rst.getString("authorId"),rst.getString("authorName"),rst.getString("text"),rst.getString("nationality"),rst.getInt("currentlyBooksWrittenQty"));

           authorList.add(author);
       }
        return authorList;
    }
}
