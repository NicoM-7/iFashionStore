package se2203b.assignments.adminapp;

import javafx.beans.property.*;

public class Department {   //A department class with appropriate attributes, getters and setters

    private final StringProperty name;
    private final ObjectProperty<Category> category;

    public Department(String name, Category category){
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleObjectProperty<>(category);
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

    public void setCategory(Category category){
        this.category.set(category);
    }
    public ObjectProperty<Category> categoryObjectProperty(){
        return category;
    }
    public Category getCategory(){
        return category.get();
    }
}
