package lk.ijse.deb.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class AuthorTm {
    private  String authorId;
    private String authorName;
    private  String text;
    private  String nationality ;
    private  int currentlyBooksWrittenQty;
}
