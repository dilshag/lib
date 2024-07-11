package lk.ijse.deb.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.deb.Util.Regex;
import lk.ijse.deb.model.AuthorDTO;
import lk.ijse.deb.bo.BOFactory;
import lk.ijse.deb.bo.custom.impl.AuthorBOImpl;
import lk.ijse.deb.tm.AuthorTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuthorController {

    @FXML
    private TableColumn<?, ?> colAuthorId;

    @FXML
    private TableColumn<?, ?> colAuthorName;

    @FXML
    private TableColumn<?, ?> colCurrentlyBooksWrittenQty;

    @FXML
    private TableColumn<?, ?> colNationality;

    @FXML
    private TableColumn<?, ?> colText;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<AuthorTm> tblAuthor;

    @FXML
    private TextField txtAuthorId;

    @FXML
    private TextField txtAuthorName;

    @FXML
    private TextField txtCurrentlyBooksWrittenQty;

    @FXML
    private TextField txtNationality;

    AuthorBOImpl authorBO = (AuthorBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.AUTHOR);
    @FXML
    private TextField txtText;
    public void initialize() {
        setCellValueFactory();
        loadAllAuthor();
    }

    private void loadAllAuthor() {

        ObservableList<AuthorTm> obList = FXCollections.observableArrayList();

        try {
            List<AuthorDTO> authorList = authorBO.getAllAuthor();
            for (AuthorDTO author: authorList) {
                AuthorTm tm = new AuthorTm(
                        author.getAuthorId(),
                        author.getAuthorName(),
                        author.getText(),
                        author.getNationality(),
                        author.getCurrentlyBooksWrittenQty()
                );

                obList.add(tm);
            }

            tblAuthor.setItems(obList);
            System.out.println(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {

            colAuthorId.setCellValueFactory(new PropertyValueFactory<>("authorId"));
            colAuthorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
            colText.setCellValueFactory(new PropertyValueFactory<>("text"));
            colNationality.setCellValueFactory(new PropertyValueFactory<>("nationality"));
            colCurrentlyBooksWrittenQty.setCellValueFactory(new PropertyValueFactory<>("currentlyBooksWrittenQty"));

        }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtAuthorId.setText("");
        txtAuthorName.setText("");
        txtText.setText("");
        txtNationality.setText("");
        txtCurrentlyBooksWrittenQty.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtAuthorId.getText();

        boolean isDeleted = authorBO.delete(id);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Author deleted!").show();
            clearFields();
            loadAllAuthor();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String authorId = txtAuthorId.getText();
        String authorName = txtAuthorName.getText();
        String text = txtText.getText();
        String nationality = txtNationality.getText();
        int currentlyBookWrittenQyt = Integer.parseInt(txtCurrentlyBooksWrittenQty.getText());


        AuthorDTO author = new AuthorDTO(authorId, authorName, text, nationality, currentlyBookWrittenQyt);
        if (isValied()) {
            boolean isSaved = authorBO.save(author);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                loadAllAuthor();
                clearFields();

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
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("RUN");
        String authorId = txtAuthorId.getText();
        String authorName = txtAuthorName.getText();
        String text =txtText.getText();
        String nationality =txtNationality.getText();
        int bookWrittenQty= Integer.parseInt(txtCurrentlyBooksWrittenQty.getText());

        AuthorDTO author = new AuthorDTO(authorId, authorName,text,nationality,bookWrittenQty);
        if (isValied()) {
            boolean isUpdated = authorBO.update(author);
            System.out.println(isUpdated);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "author updated!").show();
                clearFields();
                loadAllAuthor();
            }
        }
        else {
                // Show error message if validation fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Failed");
                alert.setContentText("Please fill in all fields correctly.");
                alert.showAndWait();
            }
    }


    @FXML
    void txtAuthorIdOnAction/* author Id search*/(ActionEvent event) throws SQLException, ClassNotFoundException {

        String authorId = txtAuthorId.getText();

        AuthorDTO author = authorBO.searchAuthorId(authorId);
        if (author != null) {
            txtAuthorId.setText(author.getAuthorId());
            txtAuthorName.setText(author.getAuthorName());
            txtText.setText(author.getText());
            txtNationality.setText(author.getNationality());
            txtCurrentlyBooksWrittenQty.setText(String.valueOf(author.getCurrentlyBooksWrittenQty()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {

        AnchorPane rootNode = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/view/dashBoardPage.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage)this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    public void txtAuthorIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.AID, txtAuthorId);
    }
    public void txtAuthorNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.NAME,txtAuthorName);
    }
    public void txtCurrentlyBooksWrittenQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.deb.Util.TextField.QUANTITY,txtCurrentlyBooksWrittenQty);
    }
    public boolean isValied(){
        boolean  aidValid  = Regex.setTextColor(lk.ijse.deb.Util.TextField.AID,txtAuthorId );

        boolean anameValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.NAME, txtAuthorName);
        boolean wqtyValid = Regex.setTextColor(lk.ijse.deb.Util.TextField.QUANTITY, txtCurrentlyBooksWrittenQty);

        return aidValid && anameValid && wqtyValid;
    }
}
