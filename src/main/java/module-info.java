module com.csia_galeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.csia_galeta to javafx.fxml, com.google.gson;
    exports com.csia_galeta;
    exports com.csia_galeta.people;
    opens com.csia_galeta.people to com.google.gson, javafx.fxml;
}