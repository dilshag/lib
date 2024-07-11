package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.MemberDTO;
import lk.ijse.deb.bo.custom.MemberBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.MemberDAOImpl;
import lk.ijse.deb.entity.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberBOImpl implements MemberBO {
    MemberDAOImpl memberDAO = (MemberDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);

    public boolean delete(String mid) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(mid);

    }

    public boolean save(MemberDTO member) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(member.getMid(),member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId()));
    }

    public boolean update(MemberDTO member) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(member.getMid(),member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId()));
    }

    public MemberDTO searchMember(String mid) throws SQLException, ClassNotFoundException {
        Member member = memberDAO.search(mid);
        return new MemberDTO(member.getMid(),member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId());
    }

    public List<MemberDTO> getAllMember() throws SQLException, ClassNotFoundException {
        List<Member>members=memberDAO.getAll();
        List<MemberDTO> memberList=new ArrayList<>();

        for (Member member:members){
            MemberDTO memberDTO = new MemberDTO(member.getMid(),member.getName(),member.getAddress(),member.getGender(),member.getTel(),member.getEmailAddress(),member.getIDNumber(),member.getFeeId());

       memberList.add(memberDTO);
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