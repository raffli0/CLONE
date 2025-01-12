module com.mycompany.simpus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.simpus.controllers to javafx.fxml;
    exports com.mycompany.simpus;
}
