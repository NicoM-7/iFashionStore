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

public class EditProductController implements Initializable {   //controller for the edit product window

    @FXML
    private Button cancelBtn, saveBtn, deleteBtn, firstConfirmBtn, secondConfirmBtn;

    @FXML
    private TextField nameField, descriptionField, brandField, sizeField, stockField, idField, priceField;

    @FXML
    private ComboBox catalogBox, itemBox, categoryField;

    private CatalogAdapter catalogAdapter;
    private ProductAdapter productAdapter;

    private Items item;

    //lists that contain data from database and are used to fill comboboxes
    final ObservableList<String> catalogs = FXCollections.observableArrayList();
    final ObservableList<Items> products = FXCollections.observableArrayList();
    final ObservableList<String> productNames = FXCollections.observableArrayList();

    public void exit() {    //method that closes the window when the user clicks the cancel button
        // Get current stage reference
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        // Close stage
        stage.close();
    }
    public void setModel(CatalogAdapter catalog, ProductAdapter product){   //method that enables methods from catalogAdapter and productAdapter class
        catalogAdapter = catalog;
        productAdapter = product;
        buildCatalogComboBoxData(); //fills the catalog box with appropriate catalog names
    }

    public void firstConfirm(){     //when the user clicks the first confirm button, get the value from the first combo box and fill in the item combo box with items from that catalog
        String catalog = catalogBox.getValue().toString();
        buildProductComboBoxData(catalog);
    }
    public void secondConfirm(){    //when the user clicks the second confirm button all the data of that product is displayed in the text fields and can be edited
        String itemName = itemBox.getValue().toString();
        for(int c = 0; c < products.size(); c++){
            if(itemName.equals(products.get(c).getName())){
                item = products.get(c);
                break;
            }
        }
        categoryField.setItems(catalogs);
        nameField.setText(item.getName());
        descriptionField.setText(item.getDescription());
        brandField.setText(item.getBrand());
        sizeField.setText(item.getSize());
        idField.setText(Integer.toString(item.getID()));
        stockField.setText(Integer.toString(item.getStock()));
        priceField.setText(Double.toString(item.getPrice()));
    }

    public void save(){     //method that updates the data base
        Items items = new Items(new Category(categoryField.getValue().toString()), nameField.getText(), descriptionField.getText(), brandField.getText(), sizeField.getText(), Integer.parseInt(stockField.getText()), Integer.parseInt(idField.getText()), Double.parseDouble(priceField.getText()));  //creates a new item based on the data the user selected and updates the database
        try {
            productAdapter.updateProduct(items, item.getName());

        }
        catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();     //closes window
    }
    public void delete() {  //method that deletes an item of choice
        try {
            productAdapter.deleteProduct(item);
        }
        catch(SQLException ex){
            displayAlert(ex.getMessage());
        }
        exit();
    }

    public void buildCatalogComboBoxData() {   //fills the list with catalog names
        try {
            catalogs.setAll(catalogAdapter.getCatalogList());
        } catch (SQLException ex) {

            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    public void buildProductComboBoxData(String catalog) {   //fills the product names
        try {
            products.setAll(productAdapter.getProductList(catalog));
            productNames.setAll(productAdapter.getProductNamesList(catalog));
            itemBox.setItems(productNames);
        } catch (SQLException ex) {

            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    private void displayAlert(String msg) {     //method that displays an error window when something goes wrong
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
    public void initialize(URL location, ResourceBundle resources) {    //method that initializes the values in combobox when the window is first opened
        catalogBox.setItems(catalogs);

    }
}

