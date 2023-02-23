package se2203b.assignments.adminapp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {     //A category class with appropriate attributes, getters and setters (Note category and catalog were assumed to be the same in this code)

    private final StringProperty name;

    public Category(String name){
        this.name = new SimpleStringProperty(name);

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

}

