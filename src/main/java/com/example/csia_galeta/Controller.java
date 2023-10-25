package com.example.csia_galeta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {
    @FXML
    private Label welcomeText;

    @FXML
    private Button addCompetitionBtn;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void openAddCompetitionWindow(){
        Competition c = new Competition();
        System.out.println("Click");
        // c.load();
        // open window

        Stage stage = (Stage) addCompetitionBtn.getScene().getWindow();
        stage.close();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Competitions.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 320, 240);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle("Hello 2!");
        newStage.setScene(scene);
        newStage.show();
    }
}