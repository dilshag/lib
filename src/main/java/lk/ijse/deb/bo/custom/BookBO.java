package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.BookDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface BookBO extends SuperBO {
    public boolean delete(String isbn) throws SQLException, ClassNotFoundException ;

    public boolean save(BookDTO book) throws SQLException, ClassNotFoundException ;

    public boolean update(BookDTO book) throws SQLException, ClassNotFoundException ;
    public BookDTO searchBook(String isbn) throws SQLException, ClassNotFoundException ;

    public List<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException ;
}
