package lk.ijse.deb.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookTm {

    private String ISBN;
    private String bookName;
    private String category;
    private String qtyOnHand;
    private String rackCode;
    private String authorId;

}
