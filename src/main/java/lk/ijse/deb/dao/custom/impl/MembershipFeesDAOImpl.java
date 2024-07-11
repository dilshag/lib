package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.MembershipFeesDAO;
import lk.ijse.deb.entity.Author;
import lk.ijse.deb.entity.MembershipFees;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipFeesDAOImpl implements MembershipFeesDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM membershipFee WHERE fee_id = ?",id);
    }

    public boolean save(MembershipFees membershipFees) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO membershipFee VALUES(?,?,?,?,?)",membershipFees.getFee_id(),membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus());
    }

    public boolean update(MembershipFees membershipFees) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE membershipFee SET name = ?, amount = ? ,date = ?, status = ?  WHERE fee_id =?",membershipFees.getName(),membershipFees.getAmount(),membershipFees.getDate(),membershipFees.getStatus(),membershipFees.getFee_id());
    }

    public MembershipFees search(String id) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM membershipFee WHERE fee_id = ?",id);
       rst.next();

       return new MembershipFees(rst.getString("fee_id"),rst.getString("name"),rst.getDouble("amount"), rst.getDate("date").toLocalDate(),rst.getString("status"));

    }

    public List<MembershipFees> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM membershipFee");
        List<MembershipFees> membershipFeesList = new ArrayList<>();

        while(rst.next()){
            MembershipFees membershipFees = new MembershipFees(rst.getString("fee_id"),rst.getString("name"),rst.getDouble("amount"), rst.getDate("date").toLocalDate(),rst.getString("status"));

            membershipFeesList.add(membershipFees);
        }
        return membershipFeesList;

    }

    public String getTotAmount() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT SUM(amount)  FROM membershipFee");

        String amount = null;
        if (rst.next()){
            amount = rst.getString(1);
        }
        return amount;
    }

    public List<String> getCode() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT fee_id  FROM membershipFee  ");
       List<String> codeList = new ArrayList<>();

    while(rst.next()){
        codeList.add(rst.getString(1));
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
