package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductAdapter {   //Class for the Products database

        Connection connection;

        public ProductAdapter(Connection conn, Boolean reset) throws SQLException {     //creates a database called Products with 8 columns
            connection = conn;
            if (reset) {
                Statement stmt = connection.createStatement();
                try {
                    // Remove tables if database tables have been created.
                    // This will throw an exception if the tables do not exist
                    // We drop Products first because it references the table Teams
                    stmt.execute("DROP TABLE Products");
                    // then do finally
                } catch (SQLException ex) {
                    // No need to report an error.
                    // The table simply did not exist.
                    // do finally to create it
                } finally {
                    // Create the table of Products
                stmt.execute("CREATE TABLE Products (Catalog CHAR(20), Name CHAR(20) UNIQUE, Description CHAR(50), Brand CHAR(20), Size CHAR(20), ID INT UNIQUE, Stock INT, Price DOUBLE)");
                }
            }
        }

        public void insertProduct(Items item) throws SQLException {     //method that takes in an item and inserts that item into the database
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO Products (Catalog, Name, Description, Brand, Size, ID, Stock, Price) VALUES ('" + item.getCategory().getName() + "','" + item.getName() + "', '" + item.getDescription() + "', '" + item.getBrand() + "', '" + item.getSize() + "' ," + item.getID() + "," + item.getStock() + ", " + item.getPrice() + ")");
        }
        public void updateProduct(Items item, String oldItemName) throws SQLException{  //method that updates the database using a new item and using the name of the old item as a filter to update
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE Products SET Catalog = '" + item.getCategory().getName() + "' WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET Description = '" + item.getDescription() + "' WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET Brand = '" + item.getBrand() + "' WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET SIZE = '" + item.getSize() + "' WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET ID = " + item.getID() + " WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET Stock = " + item.getStock() + " WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET Price = " + item.getPrice() + " WHERE NAME = '" + oldItemName + "'");
            stmt.executeUpdate("UPDATE Products SET Name = '" + item.getName() + "' WHERE NAME = '" + oldItemName + "'");
        }
        public void updateCatalog(String catalog, String oldCatalog) throws SQLException {  //method that updates the items catalog using a new catalog name and the old catalog name as filter
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE Products SET Catalog = '" + catalog + "' WHERE Catalog = '" + oldCatalog + "'");

        }
        public void deleteProduct(Items item) throws SQLException {     //method that deletes an item from the database
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM Products WHERE Name = '" + item.getName() + "'");
        }

        public ObservableList<Items> getProductList(String category) throws SQLException {   //method that returns a list of all Products in the database

            ObservableList<Items> productList = FXCollections.observableArrayList();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Products WHERE Catalog = '" + category + "'");  //gets the number of columns in the table with a specific catalog

            while(result.next()){   //loops through all rows and adds the items from the database to an observable list
                productList.add(new Items(new Category(result.getString(1)), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getInt(6), result.getInt(7), result.getDouble(8)));
            }

            return productList; //returns the list of items
        }
    public ObservableList<String> getProductNamesList(String category) throws SQLException {   //method that returns a list of all the product names

        ObservableList<String> productNameList = FXCollections.observableArrayList();
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM Products WHERE Catalog = '" + category + "'");  //gets the number of columns in the table with a specific catalog

        while(result.next()){   //loops through all rows in the table and adds the names to the list
            productNameList.add(result.getString(2));
        }

        return productNameList;     //returns the list of product names
    }
    public boolean isCatalogEmpty() throws SQLException {   //method that checks if the catalog is empty by checking if it has a row
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM Products");
            return resultSet.next();
         }
    }



