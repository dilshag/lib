package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.model.SupplierDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.impl.SupplierBOImpl;
import lk.ijse.deb.tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookSupplierController {

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colSupplierID;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblSupplierDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SupplierTm> tblSupplierDetail;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierName;
        SupplierBOImpl supplierBO = (SupplierBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }
    private void clearFields() {
        txtSupplierId.setText("");
        txtSupplierName.setText("");
        txtContactNumber.setText("");
        txtEmail.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
     String id=txtSupplierId.getText();
        try {
            boolean isDeleted = supplierBO.delete(id);
            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {

        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String tel = txtContactNumber.getText();
        String email = txtEmail.getText();

        SupplierDTO supplier = new SupplierDTO(id, name,tel,email );

        try {
            boolean isSaved = supplierBO.save(supplier);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                clearFields();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        String tel = txtContactNumber.getText();
        String email = txtEmail.getText();

        SupplierDTO supplier = new SupplierDTO(id, name, tel,email);

        try {
            boolean isUpdated = supplierBO.update(supplier);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                clearFields();
                loadAllSupplier();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() throws ClassNotFoundException {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = supplierBO.getAll();
            for (SupplierDTO supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getContactNumber(),
                        supplier.getEmail()
                );

                obList.add(tm);
            }

            tblSupplierDetail.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }




    public void txtSupplierIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtSupplierId.getText();

        SupplierDTO supplier = supplierBO.searchSupplierId(id);
        if (supplier != null) {
            txtSupplierId.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSupplierName());
            txtContactNumber.setText(supplier.getContactNumber());
            txtEmail.setText(supplier.getEmail());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }
}
