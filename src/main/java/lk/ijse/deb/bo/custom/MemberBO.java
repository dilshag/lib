package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.MemberDTO;
import lk.ijse.deb.bo.SuperBO;

import java.sql.SQLException;
import java.util.List;

public interface MemberBO extends SuperBO {
    public boolean delete(String mid) throws SQLException, ClassNotFoundException ;

    public boolean save(MemberDTO member) throws SQLException, ClassNotFoundException ;
    public boolean update(MemberDTO member) throws SQLException, ClassNotFoundException ;
    public MemberDTO searchMember(String mid) throws SQLException, ClassNotFoundException ;
    public List<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException;
}
