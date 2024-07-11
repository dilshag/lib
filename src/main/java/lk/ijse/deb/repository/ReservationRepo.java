package lk.ijse.deb.repository;

import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.ReservationDTO;
import lk.ijse.deb.tm.ReservationTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationRepo {
    public static List<ReservationTm> getAllReservation() throws SQLException {
        String sql = "SELECT * FROM reservation";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ReservationTm> reservationList = new ArrayList<>();

        while (resultSet.next()) {
            String reservationId = resultSet.getString(1);
            String borrowedDate = resultSet.getString(2);
            String dueDate = resultSet.getString(3);
            String bookReturnDate= resultSet.getString(4);
            String fineStatus  = resultSet.getString(5);
            double fineAmount = Double.parseDouble(resultSet.getString(6));
            String mid  = resultSet.getString(7);
            String ISBN  = resultSet.getString(8);


           ReservationTm reservation = new ReservationTm(reservationId,borrowedDate,dueDate,bookReturnDate,fineStatus,fineAmount,mid,ISBN);
            reservationList.add(reservation);
        }
        return reservationList;

    }

    public static boolean addReservation(ReservationTm reservation) throws SQLException {
        String sql = "INSERT INTO reservation VALUES(?, ?, ?, ?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, reservation.getReservationId());
        pstm.setObject(2, reservation.getBorrowedDate());
        pstm.setObject(3, reservation.getDueDate());
        pstm.setObject(4, reservation.getBookReturnDate());
        pstm.setObject(5, reservation.getFineStatus());
        pstm.setObject(6, reservation.getFineAmount());
        pstm.setObject(7, reservation.getMid());
        pstm.setObject(8, reservation.getISBN());

        return pstm.executeUpdate() > 0;
    }

    public static boolean deleteReservation(String reservationId) throws SQLException {
        String sql = "DELETE FROM reservation WHERE reservationId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1,reservationId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateReservation(ReservationDTO reservation) throws SQLException {
        String sql = "UPDATE  reservation SET borrowedDate=?,dueDate=?,bookReturnDate=?,fineStatus=?,fineAmount=?,mid=?,ISBN=? WHERE reservationId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setObject(1, reservation.getBorrowedDate());
        pstm.setObject(2, reservation.getDueDate());
        pstm.setObject(3, reservation.getBookReturnDate());
        pstm.setObject(4, reservation.getFineStatus());
        pstm.setObject(5, reservation.getFineAmount());
        pstm.setObject(6, reservation.getMid());
        pstm.setObject(7, reservation.getISBN());
        pstm.setObject(8, reservation.getReservationId());


        return pstm.executeUpdate() > 0;
    }

    public static ReservationDTO searchReservation(String reservationid) throws SQLException {
        String sql = "SELECT * FROM reservation WHERE reservationId=?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, reservationid);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String reservationId = resultSet.getString(1);
            String borrowedDate = resultSet.getString(2);
            String dueDate = resultSet.getString(3);
            String bookReturnDate= resultSet.getString(4);
            String fineStatus  = resultSet.getString(5);
            double fineAmount = Double.parseDouble(resultSet.getString(6));
            String mid  = resultSet.getString(7);
            String ISBN  = resultSet.getString(8);

            ReservationDTO reservation = new ReservationDTO(reservationId,borrowedDate,dueDate,bookReturnDate,fineStatus,fineAmount,mid,ISBN);


            return reservation;
        }

        return null;

    }

    public static String generateNextReservationId(String text) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("SELECT reservationId FROM reservation ORDER BY reservationId DESC LIMIT 1");

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return  splitReservationId(resultSet.getString(1));
        }
        return splitReservationId(null);
    }

    public static String splitReservationId(String currentReservationId) {
        if(currentReservationId != null){
            String[] split = currentReservationId.split("[R]");

            int reservationId = Integer.parseInt(split[1]);
            reservationId++;
            return "R" + String.format("%03d", reservationId);
        }else {
            return "R001";
        }
    }

    public static String getBookBorrowCount() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(reservationId ) FROM  reservation");

        String count =  null;
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            count = resultSet.getString(1);
        }
        return count;
    }
}
