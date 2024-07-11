package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.AuthorDTO;
import lk.ijse.deb.bo.custom.AuthorBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.AuthorDAO;
import lk.ijse.deb.entity.Author;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorBOImpl implements AuthorBO {

    AuthorDAO authorDAO= (AuthorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.AUTHOR);
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return authorDAO.delete(id);
    }

    public boolean save(AuthorDTO author) throws SQLException, ClassNotFoundException {

        return authorDAO.save(new Author(author.getAuthorId(),author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty()));
    }

    public boolean update(AuthorDTO author) throws SQLException, ClassNotFoundException {
        return authorDAO.update(new Author(author.getAuthorId(),author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty()));
    }

    public AuthorDTO searchAuthorId(String authorId) throws SQLException, ClassNotFoundException {
     Author author=authorDAO.search(authorId);
     return new AuthorDTO(author.getAuthorId(),author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty());
    }

    public List<AuthorDTO> getAllAuthor() throws SQLException, ClassNotFoundException {
        List<Author>authors = authorDAO.getAll();
        List<AuthorDTO> authorList=new ArrayList<>();

        for (Author author : authors) {
            AuthorDTO authorDTO= new AuthorDTO(author.getAuthorId(),author.getAuthorName(),author.getText(),author.getNationality(),author.getCurrentlyBooksWrittenQty());
        authorList.add(authorDTO);
        }
        return authorList;
    }
}
