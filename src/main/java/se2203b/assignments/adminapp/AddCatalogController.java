package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddCatalogController {     //Controller class for the add catalog window

    @FXML
    private Button cancelBtn, saveBtn;
    @FXML
    private TextField catalogField;

    private CatalogAdapter catalogAdapter;

    public void exit() {    //method that closes the window when the user clicks the cancel button
        // Get current stage reference
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // Close stage
        stage.close();
    }

    public void setModel(CatalogAdapter catalog) {     //Initialize catalogAdapter that allows its methods to be used in the current controller class
        catalogAdapter = catalog;
    }
    public void save(){    //method for the save button
        try {
            String catalogName = catalogField.getText();    //gets the text the user typed in the box
            catalogAdapter.insertCatalog(catalogName);  //adds the catalog to the data pase

        }
        catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();     //after the user clicks save the window closes
    }

    private void displayAlert(String msg) {     //a method that displays an alert message whenever something goes wrong
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
}
