package lk.ijse.deb.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.deb.repository.MemberRepo;
import lk.ijse.deb.repository.ReservationRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController {

    public AnchorPane ChildRoot;
    @FXML
    private Label lblAuthorCount;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;
    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBorrowCount;

    @FXML
    private Label lblMemberCount;

    @FXML
    private Label lblSupplierCount;

    @FXML
    private AnchorPane root;

    public void initialize() {
        setDate();
        setLTime();
        setMemberCount();
        setBookBorrowCount();
    }

    private void setBookBorrowCount() {
        try {
            lblBorrowCount.setText(ReservationRepo.getBookBorrowCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setMemberCount() {
        try {
            lblMemberCount.setText(MemberRepo.getMemberCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setLTime() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));

    }

    @FXML
    void btnAuthorsOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/author.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);

    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/book.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }

    @FXML
    void btnBookrackOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bookRack.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginPage.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);

    }

    @FXML
    void btnMemberOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/memberForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }

    @FXML
    void btnMembershipFeeOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/membershipFees.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);

    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/reservation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/bookSupplier.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }
    @FXML
    void btnEmailOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/emailForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ChildRoot.getChildren().clear();
        ChildRoot.getChildren().add(root);
    }

}
