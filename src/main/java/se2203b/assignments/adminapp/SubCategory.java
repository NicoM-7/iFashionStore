package se2203b.assignments.adminapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SubCategory extends Category{  //A class that extends Category class with appropriate attributes, getters and setters

    private final StringProperty subCategoryName;

    public SubCategory(Category category, String subCategoryName) {
        super(category.getName());
        this.subCategoryName = new SimpleStringProperty(subCategoryName);
    }

    public void setSubCategoryName(String subCategoryName){
        this.subCategoryName.set(subCategoryName);
    }
    public StringProperty subCategoryNameProperty(){
        return subCategoryName;
    }
    public String getSubCategoryName(){
        return subCategoryName.get();
    }

}
