module com.tazza.javafxassignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tazza.javafxassignment to javafx.fxml;
    exports com.tazza.javafxassignment;
}