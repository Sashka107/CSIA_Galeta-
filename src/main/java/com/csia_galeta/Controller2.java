package com.csia_galeta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {
    @FXML
    private Label welcomeText;

    @FXML
    public void addJudges(){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("AddJudgeFrame.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle("Frame 3!");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    public void saveCompetition(){
        CompetitionSingleton.saveTmpCompetition();
    }
}