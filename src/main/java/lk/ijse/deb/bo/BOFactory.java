package lk.ijse.deb.bo;

import lk.ijse.deb.bo.custom.impl.*;
import lk.ijse.deb.dao.custom.impl.BookRackDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
       AUTHOR,MEMBERSHIPFEES,SUPPLIER,SIGNUP,BOOKRACK,BOOK,MEMBER,RESERVATION
    }
    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case AUTHOR:
                return new AuthorBOImpl();
            case MEMBERSHIPFEES:
                return new MembershipFeesBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case SIGNUP:
                return new SignupBOImpl();
            case BOOKRACK:
                return new BookRackBOImpl();
            case BOOK:
                return new BookBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            default:
                return null;
        }
    }
}
