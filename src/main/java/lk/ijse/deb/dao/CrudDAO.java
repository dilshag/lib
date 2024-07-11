package lk.ijse.deb.dao;

import lk.ijse.deb.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T>extends SuperDAO {
    public boolean delete(String entityId) throws SQLException, ClassNotFoundException ;

    public boolean save(T entity) throws SQLException, ClassNotFoundException ;
    public boolean update(T entity) throws SQLException, ClassNotFoundException ;

    public T search(String entityId) throws SQLException, ClassNotFoundException ;

    public List<T> getAll() throws SQLException, ClassNotFoundException ;

   // List<String> getCodes() throws SQLException, ClassNotFoundException;

    //  public List<String> getCodes() throws SQLException, ClassNotFoundException ;
}
