module com.example.csia_galeta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csia_galeta to javafx.fxml;
    exports com.example.csia_galeta;
}