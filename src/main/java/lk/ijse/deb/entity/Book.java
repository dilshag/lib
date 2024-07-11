package lk.ijse.deb.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Book {
    private String ISBN;
    private String bookName;
    private String category;
    private String qtyOnHand;
    private String rackCode;
    private String authorId;
}
