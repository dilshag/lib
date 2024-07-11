package lk.ijse.deb.bo.custom.impl;

import lk.ijse.deb.model.ReservationDTO;
import lk.ijse.deb.bo.custom.ReservationBO;
import lk.ijse.deb.dao.DAOFactory;
import lk.ijse.deb.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.deb.entity.Reservation;
import lk.ijse.deb.tm.ReservationTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {
    ReservationDAOImpl reservationDAO = (ReservationDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    public String generateNextReservationId(String text) throws SQLException, ClassNotFoundException {
        return reservationDAO.generateID(text);
    }

    public List<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException {
        List<Reservation>reservations = reservationDAO.getAll();
        List<ReservationDTO> reservationList=new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO= new ReservationDTO(reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN());
            reservationList.add(reservationDTO);
        }
        return reservationList;
    }

    public boolean deleteReservation(String reservationId) throws SQLException, ClassNotFoundException {
        return reservationDAO.delete(reservationId);
    }

    public boolean updateReservation(ReservationDTO reservation) throws SQLException, ClassNotFoundException {
        return reservationDAO.update(new Reservation(reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN()));
    }

    public ReservationDTO searchReservation(String reservationId) throws SQLException, ClassNotFoundException {
        Reservation reservation =reservationDAO.search(reservationId);
        return new ReservationDTO(reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN());
    }

    public boolean addReservation(ReservationTm reservation) throws SQLException, ClassNotFoundException {
       return reservationDAO.add(new Reservation(reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN()));
    }
}
/*
 private String reservationId;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private  String fineStatus;
    private double fineAmount;
    private String mid;
    private  String ISBN;
 */
//return SQLUtil.execute("INSERT INTO reservation VALUES(?, ?, ?, ?,?,?,?,?)",reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN());