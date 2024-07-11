package lk.ijse.deb.bo.custom;

import lk.ijse.deb.model.ReservationDTO;
import lk.ijse.deb.bo.SuperBO;
import lk.ijse.deb.tm.ReservationTm;

import java.sql.SQLException;
import java.util.List;

public interface ReservationBO extends SuperBO {
    public String generateNextReservationId(String text) throws SQLException, ClassNotFoundException ;

    public List<ReservationDTO> getAllReservation() throws SQLException, ClassNotFoundException ;

    public boolean deleteReservation(String reservationId) throws SQLException, ClassNotFoundException ;
    public boolean updateReservation(ReservationDTO reservation) throws SQLException, ClassNotFoundException ;

    public ReservationDTO searchReservation(String reservationId) throws SQLException, ClassNotFoundException ;

    public boolean addReservation(ReservationTm reservation) throws SQLException, ClassNotFoundException ;
}
