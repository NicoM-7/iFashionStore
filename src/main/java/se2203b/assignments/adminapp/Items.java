package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Items {    //An item class with appropriate attributes, getters and setters

    private final StringProperty name;
    private final StringProperty description;
    private final StringProperty brand;
    private final StringProperty size;

    private final IntegerProperty stock;
    private final IntegerProperty id;

    private final DoubleProperty price;

    private final ObjectProperty<Category> category;

    public Items(Category category, String name, String description, String brand, String size, int stock, int id, double price){
        this.category = new SimpleObjectProperty<>(category);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.brand = new SimpleStringProperty(brand);
        this.size = new SimpleStringProperty(size);
        this.stock = new SimpleIntegerProperty(stock);
        this.id = new SimpleIntegerProperty(id);
        this.price = new SimpleDoubleProperty(price);
    }

    public void setName(String name){
        this.name.set(name);
    }
    public StringProperty nameProperty(){
        return name;
    }
    public String getName(){
        return name.get();
    }

    public void setDescription(String description){
        this.description.set(description);
    }
    public StringProperty descriptionProperty(){
        return description;
    }
    public String getDescription(){
        return description.get();
    }

    public void setBrand(String brand){
        this.brand.set(brand);
    }
    public StringProperty brandProperty(){
        return brand;
    }
    public String getBrand(){
        return brand.get();
    }

    public void setSize(String size){
        this.size.set(size);
    }
    public StringProperty sizeProperty(){
        return size;
    }
    public String getSize(){
        return size.get();

    }

    public void setStock(int stock){
        this.stock.set(stock);
    }
    public IntegerProperty stockProperty(){
        return stock;
    }
    public int getStock(){
        return stock.get();
    }

    public void setID(int id){
        this.id.set(id);
    }
    public IntegerProperty idProperty(){
        return id;
    }
    public int getID(){
        return id.get();
    }

    public void setPrice(double price){
        this.price.set(price);

    }
    public DoubleProperty priceProperty(){
        return price;
    }
    public double getPrice(){
        return price.get();
    }

    public void setCategory(Category category){
        this.category.set(category);
    }
    public ObjectProperty categoryProperty(){
        return category;
    }
    public Category getCategory(){
        return category.get();
    }



}
