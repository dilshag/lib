package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.BookDTO;
import lk.ijse.deb.bo.custom.BookBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.BookDAOImpl;
import lk.ijse.deb.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    BookDAOImpl bookDAO = (BookDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    public boolean delete(String isbn) throws SQLException, ClassNotFoundException {
    return bookDAO.delete(isbn);

    }

    public boolean save(BookDTO book) throws SQLException, ClassNotFoundException {
        return bookDAO.save(new Book(book.getISBN(),book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId()));
    }

    public boolean update(BookDTO book) throws SQLException, ClassNotFoundException {
        return bookDAO.update(new Book(book.getISBN(),book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId()));
    }

    public BookDTO searchBook(String isbn) throws SQLException, ClassNotFoundException {
        Book book = bookDAO.search(isbn);
        return new BookDTO(book.getISBN(),book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId());
    }

    public List<BookDTO> getAllBooks() throws SQLException, ClassNotFoundException {
       List<Book> books =  bookDAO.getAll();
       List<BookDTO> bookList = new ArrayList<>();

       for (Book book : books) {
           BookDTO bookDTO = new BookDTO(book.getISBN(),book.getBookName(),book.getCategory(),book.getQtyOnHand(),book.getRackCode(),book.getAuthorId());

      bookList.add(bookDTO);
       }
       return bookList;
    }
}
/*
private String ISBN;
private String bookName;
private String category;
private String qtyOnHand;
private String rackCode;
private String authorId;

 */