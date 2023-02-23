package se2203b.assignments.adminapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable{

    @FXML
    private MenuBar mainMenu;

    private CatalogAdapter catalogAdapter;
    private ProductAdapter productAdapter;
    private Connection conn;

    public void showAbout() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showAddProduct() throws Exception{
        catalogAdapter = new CatalogAdapter(conn, false);
        productAdapter = new ProductAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddProduct-view.fxml"));
        // create the root node
        Parent addProduct =  fxmlLoader.load();
        AddProductController addProductController = fxmlLoader.getController();
        addProductController.setModel(catalogAdapter, productAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(addProduct));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void showAddCatalog() throws Exception{
        catalogAdapter = new CatalogAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddCatalog-view.fxml"));
        // create the root node
        Parent addCatalog =  fxmlLoader.load();
        AddCatalogController catalogController = fxmlLoader.getController();
        catalogController.setModel(catalogAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(addCatalog));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void showEditProduct() throws Exception{
        catalogAdapter = new CatalogAdapter(conn, false);
        productAdapter = new ProductAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EditProduct-view.fxml"));
        // create the root node
        Parent editProduct =  fxmlLoader.load();
        EditProductController editProductController = fxmlLoader.getController();
        editProductController.setModel(catalogAdapter, productAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(editProduct));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit Product");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    public void showEditCatalog() throws Exception{
        catalogAdapter = new CatalogAdapter(conn, false);
        productAdapter = new ProductAdapter(conn, false);
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EditCatalog-view.fxml"));
        // create the root node
        Parent editCatalog =  fxmlLoader.load();
        EditCatalogController editCatalogController = fxmlLoader.getController();
        editCatalogController.setModel(catalogAdapter, productAdapter);
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(editCatalog));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void displayAlert(String msg) {
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

    public void resetDB() {
        try {
            // creates Catalog model
            catalogAdapter = new CatalogAdapter(conn, true);
            displayAlert("Catalog data base has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
        try {
            // create Products model
            productAdapter = new ProductAdapter(conn, true);
            displayAlert("Product data base has been reset");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }


    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:TeamDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }

    }
}
