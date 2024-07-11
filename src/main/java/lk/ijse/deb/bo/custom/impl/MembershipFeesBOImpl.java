package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.MembershipFeesDTO;
import lk.ijse.deb.bo.custom.MembershipFeesBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.MembershipFeesDAOImpl;
import lk.ijse.deb.entity.MembershipFees;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeesBOImpl implements MembershipFeesBO {
    MembershipFeesDAOImpl membershipFeesDAO = (MembershipFeesDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIPFEES);

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return membershipFeesDAO.delete(id);

    }

    public boolean save(MembershipFeesDTO membershipFees) throws SQLException, ClassNotFoundException {
        return membershipFeesDAO.save(new MembershipFees(membershipFees.getFee_id(),membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus()));
    }

    public boolean update(MembershipFeesDTO membershipFees) throws SQLException, ClassNotFoundException {
        return membershipFeesDAO.update(new MembershipFees(membershipFees.getFee_id(),membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus()));
    }

    public MembershipFeesDTO searchId(String id) throws SQLException, ClassNotFoundException {
        MembershipFees membershipFees = membershipFeesDAO.search(id);
        return new MembershipFeesDTO(membershipFees.getFee_id(),membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus());
    }

    public List<MembershipFeesDTO> getAll() throws SQLException, ClassNotFoundException {
        List<MembershipFees>membershipFeess =membershipFeesDAO.getAll();
        List<MembershipFeesDTO> membershipFeesList =new ArrayList<>();

        for(MembershipFees membershipFees:membershipFeess){
            MembershipFeesDTO membershipFeesDTO = new MembershipFeesDTO(membershipFees.getFee_id(),membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus());

        membershipFeesList.add(membershipFeesDTO);
        }
        return membershipFeesList;
    }

    public String getTotalAmount() throws SQLException, ClassNotFoundException {
        return membershipFeesDAO.getTotAmount();

    }

    public List<String> getCodes() throws SQLException, ClassNotFoundException {
        List<String> codes = membershipFeesDAO.getCode();
        List<String> codeList = new ArrayList<>();

        for(String code:codes){
            codeList.add(code);

        }
        return codeList;
    }
}
/*
 private String fee_id ;
    private String name;
    private double amount;
    private LocalDate date;
    private String status;
 */