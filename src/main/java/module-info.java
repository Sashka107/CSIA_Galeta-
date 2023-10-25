module com.example.csia_galeta {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.csia_galeta to javafx.fxml, com.google.gson;
    exports com.example.csia_galeta;
    exports com.example.csia_galeta.people;
    opens com.example.csia_galeta.people to com.google.gson, javafx.fxml;
}