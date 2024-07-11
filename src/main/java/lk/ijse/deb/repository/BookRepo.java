package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.BookDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepo {

   public static List<BookDTO> getAllBooks() throws SQLException {
        String sql = "SELECT * FROM book";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<BookDTO> bookList = new ArrayList<>();

        while (resultSet.next()) {
            String ISBN = resultSet.getString(1);
            String bookName = resultSet.getString(2);
            String category = resultSet.getString(3);
            String qtyOnHand = resultSet.getString(4);
            String rackCode = resultSet.getString(5);
            String authorId = resultSet.getString(6);

            BookDTO book = new BookDTO(ISBN,bookName,category,qtyOnHand,rackCode,authorId);
            bookList.add(book);

        }
        return bookList;    }
/*
    public static boolean delete(String isbn) throws SQLException {
        String sql = "DELETE FROM book WHERE ISBN=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,isbn);

        return pstm.executeUpdate() > 0;    }






    public static boolean save(BookDTO book) throws SQLException {
        String sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,book.getISBN());
        pstm.setObject(2,book.getBookName());
        pstm.setObject(3,book.getCategory());
        pstm.setObject(4,book.getQtyOnHand());
        pstm.setObject(5,book.getRackCode());
        pstm.setObject(6,book.getAuthorId());
        return pstm.executeUpdate() > 0;
    }



    public static boolean update(BookDTO book) throws SQLException {
        String sql = "UPDATE book SET bookName =?, category = ?, qtyOnHand = ?, rackCode = ?,authorId = ? WHERE ISBN =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1,book.getBookName());
        pstm.setObject(2,book.getCategory());
        pstm.setObject(3,book.getQtyOnHand());
        pstm.setObject(4,book.getRackCode());
        pstm.setObject(5,book.getAuthorId());
        pstm.setObject(6,book.getISBN());

        return pstm.executeUpdate() > 0;    }

     */

    public static BookDTO searchBook(String isbn) throws SQLException {
        String sql = "SELECT * FROM book WHERE ISBN =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, isbn);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String ISBN= resultSet.getString(1);
            String bookName = resultSet.getString(2);
            String category = resultSet.getString(3);
            String qtyOnHand = resultSet.getString(4);
            String rackCode = resultSet.getString(5);
            String authorId = resultSet.getString(6);

            BookDTO book = new BookDTO(ISBN,bookName,category,qtyOnHand,rackCode,authorId);


            return book;
        }

        return null;    }




    public static boolean UPDATEQTY(String isbn) throws SQLException {
        String sql = "UPDATE book SET qtyOnHand = (qtyOnHand-1) WHERE ISBN = ?";
            Connection connection = DbConnection.getInstance().getConnection();

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, isbn);

            return pstm.executeUpdate() > 0;
    }
}
