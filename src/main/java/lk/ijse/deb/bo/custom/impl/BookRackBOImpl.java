package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.BookRackDTO;
import lk.ijse.deb.bo.custom.BookRackBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.BookRackDAOImpl;
import lk.ijse.deb.entity.BookRack;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRackBOImpl implements BookRackBO  {

    BookRackDAOImpl bookRackDAO = (BookRackDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOKRACK);

    public boolean delete(String rackCode) throws SQLException, ClassNotFoundException {
      return bookRackDAO.delete(rackCode);
    }

    public boolean save(BookRackDTO bookRack) throws SQLException, ClassNotFoundException {
        return bookRackDAO.save(new BookRack(bookRack.getRackCode(),bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks()));
    }

    public boolean update(BookRackDTO bookRack) throws SQLException, ClassNotFoundException {
        return bookRackDAO.update(new BookRack(bookRack.getRackCode(),bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks()));
    }

    public BookRackDTO searchBookRack(String rackCode) throws SQLException, ClassNotFoundException {
        BookRack bookRack=bookRackDAO.search(rackCode);
        return new BookRackDTO(bookRack.getRackCode(),bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks());
    }

    public List<BookRackDTO> getAll() throws SQLException, ClassNotFoundException {
        List<BookRack>bookRacks=bookRackDAO.getAll();
        List<BookRackDTO>bookRackList=new ArrayList<>();

        for(BookRack bookRack:bookRacks){
            BookRackDTO bookRackDTO=new BookRackDTO(bookRack.getRackCode(),bookRack.getQtyBooks(),bookRack.getCategoryOfBooks(),bookRack.getNameOfBooks());
            bookRackList.add(bookRackDTO);
        }
       return bookRackList;

    }

    public List<String> getRackCode() throws SQLException, ClassNotFoundException {
        List<String> codes = bookRackDAO.getRackCode();
        List<String> codeList = new ArrayList<>();

        for(String code:codes){
            codeList.add(code);

        }
        return codeList;
    }
}

