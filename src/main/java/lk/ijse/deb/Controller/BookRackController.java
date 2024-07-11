package lk.ijse.deb.Controller;

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
import lk.ijse.deb.model.BookRackDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.impl.BookRackBOImpl;
import lk.ijse.deb.tm.BookRackTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookRackController {

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colNameOfBooks;

    @FXML
    private TableColumn<?, ?> colQuantityBooks;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookRackTm> tblBookrack;

    @FXML
    private TextField txtCategoryOfBooks;

    @FXML
    private TextField txtCode;

    @FXML
    private TextArea txtNameOfBooks;

    @FXML
    private TextField txtQuantity;

    BookRackBOImpl bookRackBO = (BookRackBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOKRACK);

    public void initialize() throws ClassNotFoundException {
        setCellValueFactory();
        loadAllBookRack();
    }


    private void setCellValueFactory() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("rackCode"));
        colQuantityBooks.setCellValueFactory(new PropertyValueFactory<>("qtyBooks"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryOfBooks"));
        colNameOfBooks.setCellValueFactory(new PropertyValueFactory<>("nameOfBooks"));
    }

    private void loadAllBookRack() throws ClassNotFoundException {
        ObservableList<BookRackTm> obList = FXCollections.observableArrayList();

        try {
            List<BookRackDTO> bookRackList = bookRackBO.getAll();
            for (BookRackDTO bookRack : bookRackList) {
                BookRackTm tm = new BookRackTm(
                        bookRack.getRackCode(),
                        bookRack.getQtyBooks(),
                        bookRack.getCategoryOfBooks(),
                        bookRack.getNameOfBooks()
                );

                obList.add(tm);
            }

            tblBookrack.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String rackCode = txtCode.getText();
        String qtyBooks = txtQuantity.getText();
        String categoryOfBooks = txtCategoryOfBooks.getText();
        String nameOfBooks = txtNameOfBooks.getText();


        BookRackDTO bookRack = new BookRackDTO(rackCode, qtyBooks, categoryOfBooks, nameOfBooks);
        if (isValied()) {
            try {
                boolean isSaved = bookRackBO.save(bookRack);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!").show();
                    loadAllBookRack();
                    clearFields();
                    loadAllBookRack();
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
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String rackCode = txtCode.getText();
        String qtyBooks = txtQuantity.getText();
        String categoryOfBooks = txtCategoryOfBooks.getText();
        String nameOfBooks = txtNameOfBooks.getText();


        BookRackDTO bookRack = new BookRackDTO(rackCode,qtyBooks,categoryOfBooks,nameOfBooks);
        if (isValied()) {

            try {
                boolean isUpdated = bookRackBO.update(bookRack);

                if (isUpdated) {
                    // System.out.println(isUpdated);
                    new Alert(Alert.AlertType.CONFIRMATION, "updated!").show();
                    clearFields();
                    loadAllBookRack();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            // Show error message if validation fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();
    }

    private void clearFields() {
        txtCode.setText("");
        txtNameOfBooks.setText("");
        txtQuantity.setText("");
        txtCategoryOfBooks.setText("");

    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String rackCode = txtCode.getText();

        boolean isDeleted = bookRackBO.delete(rackCode);
        if (isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            clearFields();
            loadAllBookRack();
        }
    }


    @FXML
    void txtCodeOnAction(ActionEvent event) throws ClassNotFoundException {
        String rackCode = txtCode.getText();

        try {
            BookRackDTO bookRack = bookRackBO.searchBookRack(rackCode);
            if (bookRack != null) {
                txtCode.setText(bookRack.getRackCode());
                txtNameOfBooks.setText(bookRack.getNameOfBooks());
                txtQuantity.setText(bookRack.getQtyBooks());
                txtCategoryOfBooks.setText(bookRack.getCategoryOfBooks());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Error..Not found!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtCodeOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.BRID, txtCode);
    }

    public void txtQuantityOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.QUANTITY, txtQuantity);
    }

   /*public void txtNameOfBooksOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.AID, txtCategoryOfBooks);
    }*/
    public boolean isValied(){
        boolean bookrackidValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.BRID,txtCode );
       boolean bookqtyValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.QUANTITY,txtQuantity );
       // boolean booknameValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.NAME,txtNameOfBooks );

        return bookrackidValid && bookqtyValid;
    }
}