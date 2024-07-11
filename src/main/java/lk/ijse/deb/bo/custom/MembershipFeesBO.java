package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.MembershipFeesDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface MembershipFeesBO extends SuperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean save(MembershipFeesDTO membershipFees) throws SQLException, ClassNotFoundException ;

    public boolean update(MembershipFeesDTO membershipFees) throws SQLException, ClassNotFoundException ;
    public MembershipFeesDTO searchId(String id) throws SQLException, ClassNotFoundException ;

    public List<MembershipFeesDTO> getAll() throws SQLException, ClassNotFoundException ;
    public String getTotalAmount() throws SQLException, ClassNotFoundException ;

    public List<String> getCodes() throws SQLException, ClassNotFoundException;
}
