package lk.ijse.deb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String contactNumber;
    private String email;
}
