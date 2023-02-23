package se2203b.assignments.adminapp;

import javafx.beans.property.*;



public class Account {      //An account class with appropriate attributes, getters and setters

    private final StringProperty email;
    private final StringProperty password;
    private final StringProperty firstName;
    private final StringProperty lastName;

    private final IntegerProperty accountID;

    public Account(String email, String password, String firstName, String lastName, int accountID){
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.accountID = new SimpleIntegerProperty(accountID);
    }

    public void setEmail(String email){
        this.email.set(email);
    }
    public StringProperty emailProperty(){
        return email;
    }
    public String getEmail(){
        return email.get();
    }

    public void setPassword(String password){
        this.password.set(password);
    }
    public StringProperty passwordProperty(){
        return password;
    }
    public String getPassword(){
        return password.get();
    }

    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    public StringProperty firstNameProperty(){
        return firstName;
    }
    public String getFirstName(){
        return firstName.get();
    }

    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    public StringProperty lastNameProperty(){
        return lastName;
    }
    public String getLastName(){
        return lastName.get();
    }

    public void setAccountID(int id){
        this.accountID.set(id);
    }
    public IntegerProperty accountIDProperty(){
        return accountID;
    }
    public int getAccountID(){
        return accountID.get();
    }



}
