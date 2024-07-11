package lk.ijse.deb.dao;

import lk.ijse.deb.bo.custom.impl.ReservationBOImpl;
import lk.ijse.deb.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes {
        AUTHOR,MEMBERSHIPFEES,SUPPLIER,SIGNUP,BOOKRACK,BOOK,MEMBER,RESERVATION
    }
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case AUTHOR:
                return new AuthorDAOImpl();
            case MEMBERSHIPFEES:
                return new MembershipFeesDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SIGNUP:
                return new SignupDAOImpl();
            case BOOKRACK:
                return new BookRackDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            default:
                return null;
        }
    }
}
