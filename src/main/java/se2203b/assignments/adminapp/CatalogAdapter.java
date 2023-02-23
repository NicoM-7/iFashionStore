package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CatalogAdapter {   //Class that contains methods for the catalogs database

    Connection connection;

    public CatalogAdapter(Connection conn, Boolean reset) throws SQLException {     //creates a database of name Catalogs that takes in a String of 17 characters or less that must be unique
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
                // This will throw an exception if the tables do not exist
                // We drop Matches first because it references the table Teams
                stmt.execute("DROP TABLE Catalogs");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Catalogs
                stmt.execute("CREATE TABLE Catalogs (Name CHAR(17) NOT NULL PRIMARY KEY)");
            }
        }
    }

    public void insertCatalog(String catalog) throws SQLException {    //method that inserts a catalog into the database
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Catalogs (Name) VALUES ('" + catalog +"')");
    }
    public void updateCatalog(String catalog, String oldCatalog) throws SQLException {  //method that updates an old catalog name with a new catalog name
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("UPDATE Catalogs SET Name = '" + catalog + "' WHERE Name = '" + oldCatalog + "'");

    }

    public ObservableList<String> getCatalogList() throws SQLException {   //method that returns a list of all catalog names

        ObservableList<String> catalogList = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Catalogs");  //gets the number of columns in the table

        while(result.next()){   //loops through all rows in the table and adds the catalog to observable list
            catalogList.add(result.getString(1));
        }

        return catalogList;     //returns the catalog list
    }
    public void deleteCatalog(String catalog) throws SQLException {     //deletes a catalog of specific name from the database
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM Catalogs WHERE Name = '" + catalog + "'");
    }
}
