package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.SignupDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;

public interface SignupBO extends SuperBO {
    public boolean register(SignupDTO signup) throws SQLException, ClassNotFoundException;
}
