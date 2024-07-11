package lk.ijse.deb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SignupDTO {
    private String Type;
    private String firstName;
    private String lastName;
    private String nic;
    private String email;
    private String phonenumber;
    private String username;
    private String password;
}
