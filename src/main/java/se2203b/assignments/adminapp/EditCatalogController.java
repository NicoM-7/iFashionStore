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

public class EditCatalogController implements Initializable {   //controller class for the editcatalog window

    @FXML
    private Button cancelBtn, saveBtn, deleteBtn;

    @FXML
    private TextField catalogField;

    @FXML
    private ComboBox catalogBox;

    private CatalogAdapter catalogAdapter;
    private ProductAdapter productAdapter;

    final ObservableList<String> catalogs = FXCollections.observableArrayList();

    public void exit() {    //closes the window when the user clicks the cancel button
        // Get current stage reference
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // Close stage
        stage.close();
    }
    public void setModel(CatalogAdapter catalog, ProductAdapter product){   //gets methods from the catalogAdapter and productAdapter classes
        catalogAdapter = catalog;
        productAdapter = product;
        buildCatalogComboBoxData(); //gets the values for the catalog combo box
    }
    public void buildCatalogComboBoxData() {   //fills the catalog combo box with its proper values
        try {
            catalogs.setAll(catalogAdapter.getCatalogList());
        } catch (SQLException ex) {

            displayAlert("ERROR: " + ex.getMessage());
        }
    }
    private void displayAlert(String msg) {     //method that displays an error window depending on the error that occurs
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
    public void confirm(){      //method for the confirm button, used to save the catalog the user selects and
        String catalog = catalogBox.getValue().toString();
        catalogField.setText(catalog);
    }
    public void save(){     //method that updates the catalogs name in the catalog and product database using the old catalog name and the new catalog name
        try {
            productAdapter.updateCatalog(catalogField.getText(), catalogBox.getValue().toString());
            catalogAdapter.updateCatalog(catalogField.getText(), catalogBox.getValue().toString());
        }
        catch (SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();     //closes the window
    }
    public void delete() {      //method that deletes an existing catalog from the database
        try{
            if(!productAdapter.isCatalogEmpty()){
                catalogAdapter.deleteCatalog(catalogBox.getValue().toString());
            }
        }
        catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {    //method that initializes the combobox with valu
        catalogBox.setItems(catalogs);
    }
}
