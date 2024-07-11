package lk.ijse.deb.dao.custom.impl;

import lk.ijse.deb.dao.SQLUtil;
import lk.ijse.deb.dao.custom.ReservationDAO;
import lk.ijse.deb.entity.Author;
import lk.ijse.deb.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.deb.repository.ReservationRepo.splitReservationId;

public class ReservationDAOImpl implements ReservationDAO {
    public String generateID(String text) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT reservationId FROM reservation ORDER BY reservationId DESC LIMIT 1");
        if (rst.next()) {

            return splitReservationId(rst.getString(1));
        }
        return splitReservationId(null);
    }

    public List<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM reservation");
        List<Reservation> reservationList = new ArrayList<>();

        while (rst.next()) {
            Reservation reservation = new Reservation(
                    rst.getString("reservationId"),
                    rst.getString("borrowedDate"),
                    rst.getString("dueDate"),
                    rst.getString("bookReturnDate"),
                    rst.getString("fineStatus"),
                    rst.getDouble("fineAmount"),
                    rst.getString("mid"),
                    rst.getString("ISBN")
            );


            reservationList.add(reservation);
        }
        return reservationList;
    }

    public boolean delete(String reservationId) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM reservation WHERE reservationId=?", reservationId);
    }

    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    public boolean update(Reservation reservation) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE  reservation SET borrowedDate=?,dueDate=?,bookReturnDate=?,fineStatus=?,fineAmount=?,mid=?,ISBN=? WHERE reservationId=?",reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN(),reservation.getReservationId());
    }

    public Reservation search(String reservationId) throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtil.execute("SELECT * FROM reservation WHERE reservationId=?",reservationId);
    rst.next();
    return new Reservation(
            rst.getString("reservationId"),
            rst.getString("borrowedDate"),
            rst.getString("dueDate"),
            rst.getString("bookReturnDate"),
            rst.getString("fineStatus"),
            rst.getDouble("fineAmount"),
            rst.getString("mid"),
            rst.getString("ISBN")
    );

    }

    public boolean add(Reservation reservation) throws SQLException, ClassNotFoundException {
       return SQLUtil.execute("INSERT INTO reservation VALUES(?, ?, ?, ?,?,?,?,?)",reservation.getReservationId(),reservation.getBorrowedDate(),reservation.getDueDate(),reservation.getBookReturnDate(),reservation.getFineStatus(),reservation.getFineAmount(),reservation.getMid(),reservation.getISBN());
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