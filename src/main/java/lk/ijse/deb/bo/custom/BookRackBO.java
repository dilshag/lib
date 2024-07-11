package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.BookRackDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface BookRackBO extends SuperBO {
    public boolean delete(String rackCode) throws SQLException, ClassNotFoundException ;
    public boolean save(BookRackDTO bookRack) throws SQLException, ClassNotFoundException;
    public boolean update(BookRackDTO bookRack) throws SQLException, ClassNotFoundException ;
    public BookRackDTO searchBookRack(String rackCode) throws SQLException, ClassNotFoundException ;
    public List<BookRackDTO> getAll() throws SQLException, ClassNotFoundException ;
    public List<String> getRackCode() throws SQLException, ClassNotFoundException ;
}
