package lk.ijse.deb.entity;


import lk.ijse.deb.tm.MemberTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private String mid;
    private String name;
    private String address;
    private String gender;
    private String tel;
    private String EmailAddress;
    private String IDNumber;
    private String feeId;
}
