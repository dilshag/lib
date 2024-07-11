package lk.ijse.deb.Controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.Util.Regex;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.impl.MembershipFeesBOImpl;
import lk.ijse.deb.db.DbConnection;
import lk.ijse.deb.model.MembershipFeesDTO;
import lk.ijse.deb.tm.MembershipFeesTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MembershipFeesController {

    @FXML
    private ToggleGroup Status;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colFeeid;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Label lblPaidDate;

    @FXML
    private Label lblTotalAmount;

    @FXML
    private ToggleGroup paidAmount;

    @FXML
    private JFXRadioButton rButtonAmountAnually;

    @FXML
    private JFXRadioButton rButtonAmountSixMonths;

    @FXML
    private JFXRadioButton rButtonAmountmonthly;

    @FXML
    private JFXRadioButton rButtonAnually;

    @FXML
    private JFXRadioButton rButtonMonthly;

    @FXML
    private JFXRadioButton rButtonSixMonths;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MembershipFeesTm> tblMembershipFee;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStatus;

    MembershipFeesBOImpl membershipFeesBO = (MembershipFeesBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MEMBERSHIPFEES);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("DashBoard Form");

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        setDate();
      //  generateNextMembershipFeeId();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAmount.setText("");
        lblPaidDate.setText("");
        txtStatus.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtId.getText();

        try {

            boolean isDeleted = membershipFeesBO.delete(id);
            System.out.println(isDeleted);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "MemberFee deleted!").show();
                clearFields();
                setDate();
                setTotalAmount();
                loadAllMembershipFee();
                setCellValueFactory();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        LocalDate date = LocalDate.parse(lblPaidDate.getText());
        String status = txtStatus.getText();

        MembershipFeesDTO membershipFees = new MembershipFeesDTO(id, name, amount, date, status);
        if (isValied()) {
            try {
                boolean isSaved = membershipFeesBO.save(membershipFees);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "membership fees saved!").show();
                    clearFields();
                    setDate();
                    setTotalAmount();
                    loadAllMembershipFee();
                    setCellValueFactory();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        LocalDate date = LocalDate.parse(lblPaidDate.getText());
        String status = txtStatus.getText();

        MembershipFeesDTO membershipFees = new MembershipFeesDTO(id, name, amount, date, status);
        if (isValied()) {
            try {
                boolean isUpdated = membershipFeesBO.update(membershipFees);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
                    clearFields();
                    setDate();
                    setTotalAmount();
                    loadAllMembershipFee();
                    setCellValueFactory();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    @FXML
    void getAmount(ActionEvent event) {
        if (rButtonAmountmonthly.isSelected()){
            txtAmount.setText(rButtonAmountmonthly.getText());
        } else if (rButtonAmountSixMonths.isSelected()) {
            txtAmount.setText(rButtonAmountSixMonths.getText());
        } else if (rButtonAmountAnually.isSelected()) {
            txtAmount.setText(rButtonAmountAnually.getText());
        }
    }
    @FXML
    void getStatus(ActionEvent event) {
        if (rButtonMonthly.isSelected()){
            txtStatus.setText(rButtonMonthly.getText());
        }else if (rButtonSixMonths.isSelected()){
            txtStatus.setText(rButtonSixMonths.getText());
        } else if (rButtonAnually.isSelected()) {
            txtStatus.setText(rButtonAnually.getText());
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        MembershipFeesDTO membershipFees = membershipFeesBO.searchId(id);
        if (membershipFees != null) {
            txtId.setText(membershipFees.getFee_id());
            txtName.setText(membershipFees.getName());
            txtAmount.setText(String.valueOf(membershipFees.getAmount()));
            lblPaidDate.setText(String.valueOf(membershipFees.getDate()));
            txtStatus.setText(membershipFees.getStatus());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "member fee not found!").show();
        }
    }

    public  void initialize() throws SQLException, ClassNotFoundException {
        setDate();
       loadAllMembershipFee();
        setCellValueFactory();
      setTotalAmount();

        //tableListener();

    }


    private void setTotalAmount() throws ClassNotFoundException {
        try {
            lblTotalAmount.setText(membershipFeesBO.getTotalAmount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colFeeid.setCellValueFactory(new PropertyValueFactory<>("fee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));




    }


    private void loadAllMembershipFee() throws ClassNotFoundException {
        ObservableList<MembershipFeesTm> obList = FXCollections.observableArrayList();

        try {
            List<MembershipFeesDTO> membershipFeesList = membershipFeesBO.getAll();
            for (MembershipFeesDTO membershipFees : membershipFeesList) {
                MembershipFeesTm tm = new MembershipFeesTm(
                        membershipFees.getFee_id(),
                        membershipFees.getName(),
                        membershipFees.getAmount(),
                        membershipFees.getDate(),
                        membershipFees.getStatus()
                );

                obList.add(tm);
            }

            tblMembershipFee.setItems(obList);
        //    System.out.println(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {

        lblPaidDate.setText(String.valueOf(LocalDate.now()));
    }


    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.MFEEID,txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.NAME,txtName);
    }

    public boolean isValied(){
        boolean mnameValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.NAME, txtName);
        boolean feeidValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.MFEEID, txtId );

        return mnameValid && feeidValid;
    }

    public void btnPrintOnAction(ActionEvent actionEvent) throws SQLException, JRException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/report/membershipfee.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("MFID","FOO2");//txtAmount.getText()

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
}
