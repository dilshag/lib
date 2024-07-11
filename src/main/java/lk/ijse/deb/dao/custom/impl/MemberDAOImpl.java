package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.MemberDAO;
import lk.ijse.deb.entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    public boolean delete(String mid) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM member WHERE  mid    = ?",mid);
    }

    public boolean save(Member member) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO member VALUES(?, ?, ?, ?, ?,?,?,?)",member.getMid(),member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId());
    }

    public boolean update(Member member) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE member SET name=?, address=?, gender=? ,tel =? ,EmailAddress =?,IDNumber=?, feeId=? WHERE  mid=?",member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId(),member.getMid());

    }

    public Member search(String mid) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM member WHERE mid = ?",mid);
        rst.next();
        return new Member(rst.getString("mid"),rst.getString("name"),rst.getString("address"),rst.getString("gender"),rst.getString("tel"),rst.getString("EmailAddress"),rst.getString("IDNumber"),rst.getString("feeId"));
    }

    public List<Member> getAll() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM member");
       List<Member> memberList = new ArrayList<>();

       while (rst.next()) {
           Member member = new Member(rst.getString("mid"),rst.getString("name"),rst.getString("address"),rst.getString("gender"),rst.getString("tel"),rst.getString("EmailAddress"),rst.getString("IDNumber"),rst.getString("feeId"));

       memberList.add(member);
       }
       return memberList;
    }
}
/*
 private String mid;
    private String name;
    private String address;
    private String gender;
    private String tel;
    private String EmailAddress;
    private String IDNumber;
    private String feeId;
 */