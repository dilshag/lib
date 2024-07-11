package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.SignupDTO;
import lk.ijse.deb.bo.custom.SignupBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.SignupDAOImpl;
import lk.ijse.deb.entity.Signup;

import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {
    SignupDAOImpl signupDAO = (SignupDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SIGNUP);

    public boolean register(SignupDTO signup) throws SQLException, ClassNotFoundException {
        return signupDAO.register(new Signup(signup.getType(),signup.getFirstName(),signup.getLastName(),signup.getNic(),signup.getEmail(),signup.getPhonenumber(),signup.getUsername(),signup.getPassword()));

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