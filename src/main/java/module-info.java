module se2203b.assignments.adminapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens se2203b.assignments.adminapp to javafx.fxml;
    exports se2203b.assignments.adminapp;
}