package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.AuthorDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface AuthorBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean save(AuthorDTO author) throws SQLException, ClassNotFoundException ;

    public boolean update(AuthorDTO author) throws SQLException, ClassNotFoundException ;

    public AuthorDTO searchAuthorId(String authorId) throws SQLException, ClassNotFoundException ;

    public List<AuthorDTO> getAllAuthor() throws SQLException, ClassNotFoundException ;
}
