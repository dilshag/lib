package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.SignupDAO;
import lk.ijse.deb.entity.Signup;

import java.sql.SQLException;
import java.util.List;

public class SignupDAOImpl implements SignupDAO {

    public boolean register(Signup signup) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Admin VALUES(?, ?, ?,?,?,?,?,?)",signup.getType(),signup.getFirstName(),signup.getLastName(),signup.getNic(),signup.getEmail(),signup.getPhonenumber(),signup.getUsername(),signup.getPassword());
    }

    @Override
    public boolean delete(String entityId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Signup entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Signup entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Signup search(String entityId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Signup> getAll() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
/*
 private String Type;
    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private String phonenumber;
    private String username;
    private String password;
 */