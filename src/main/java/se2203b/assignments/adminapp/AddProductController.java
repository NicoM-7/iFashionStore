package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddProductController implements Initializable {    //controller for the AddProduct window

    @FXML
    private Button cancelBtn, saveBtn;

    @FXML
    private TextField nameField, descriptionField, brandField, sizeField, stockField, idField, priceField;

    private CatalogAdapter catalogAdapter;
    private ProductAdapter productAdapter;
    @FXML
    private ComboBox catalogBox;

    final ObservableList<String> data = FXCollections.observableArrayList();    //holds combobox items

    public void exit() {    //method for the cancel button that closes window
        // Get current stage reference
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // Close stage
        stage.close();
    }

    public void save(){     //method for the save button
        Items items = new Items(new Category(catalogBox.getValue().toString()), nameField.getText(), descriptionField.getText(), brandField.getText(), sizeField.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(idField.getText()), Double.parseDouble(priceField.getText()));     //gets all the text values the user typed in the box and creates a new item object
        try {
            productAdapter.insertProduct(items);    //inserts the item into the database

        }
        catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();     //closes window
    }

    public void setModel(CatalogAdapter catalog, ProductAdapter product) { //initializes variables to allow using methods from other classes in the controller
        catalogAdapter = catalog;
        productAdapter = product;
        buildComboBoxData();
    }

    public void buildComboBoxData() {   //fills the list with the catalog names
        try {
            data.addAll(catalogAdapter.getCatalogList());
        } catch (SQLException ex) {

            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String msg) {     //method that displays an error window if something goes wrong
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/lab5/tennisballgames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        catalogBox.setItems(data);  //sets the values of combobox equal to the list that has the catalog names
    }
}
