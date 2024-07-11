package lk.ijse.deb.entity;
import lk.ijse.deb.model.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Author {
    private  String authorId;
    private String  authorName;
    private String text;
    private String nationality;
    private int currentlyBooksWrittenQty;

    public Author(AuthorDTO author) {
    }
}

