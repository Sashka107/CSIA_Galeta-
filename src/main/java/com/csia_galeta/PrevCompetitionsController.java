package com.csia_galeta;

import com.csia_galeta.controllers.CompetitionViewController;

import com.csia_galeta.ser.SceneOpener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class PrevCompetitionsController {

    @FXML
    public ListView<Competition> savedCompetitions = new ListView<>();

    @FXML
    private Button backBtn;

    public void showCompetitions(List<Competition> data) {
        savedCompetitions.getItems().addAll(data);
        savedCompetitions.setOnMouseClicked(new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CompetitionViewController controller = SceneOpener.openSceneAndReturnController("PrevCompetitionView.fxml",
                        "Saved Competition View",
                        savedCompetitions.getScene().getWindow());
                controller.load(savedCompetitions.getSelectionModel().getSelectedItem());
            }
        });
    }

    @FXML
    private void backToMainMenu(){
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", backBtn.getScene().getWindow());
    }

}