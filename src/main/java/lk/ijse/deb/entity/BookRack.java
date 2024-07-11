package lk.ijse.deb.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookRack {
    private String rackCode;
    private  String qtyBooks ;
    private  String categoryOfBooks;
    private  String nameOfBooks;
}
