package lk.ijse.deb.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

public class MembershipFees {
    private String fee_id ;
    private String name;
    private double amount;
    private LocalDate date;
    private String status;
}
