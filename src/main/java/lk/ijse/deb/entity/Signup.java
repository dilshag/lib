package lk.ijse.deb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Signup {
    private String Type;
    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private String phonenumber;
    private String username;
    private String password;

    public Signup(String firstName, String lastName, String nic, String email, String phonenumber, String username, String password) {
    }
}
