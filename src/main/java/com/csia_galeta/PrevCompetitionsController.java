package com.csia_galeta;

import com.csia_galeta.controllers.CompetitionViewController;

import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

/*
 Class PrevCompetitionsController
 This class is the controller for the previous competitions window
 */
public class PrevCompetitionsController {

    @FXML
    public ListView<Competition> savedCompetitions = new ListView<>(); // Variable-reference to the list of competitions.

    @FXML
    private Button backBtn; // Variable-reference to the Back button.

    /*
     Method for loading competitions into the list on the UI

     @param data the list of competitions to load onto the UI
     */
    public void showCompetitions(List<Competition> data) {

        // Set the list on the UI.
        savedCompetitions.getItems().addAll(data);

        // Set the action for clicking on a competition in the list.
        savedCompetitions.setOnMouseClicked(mouseEvent -> {

            // Upon clicking, open a window with a preview of competition information.
            CompetitionViewController controller = SceneOpener.openSceneAndReturnController("PrevCompetitionView.fxml",
                    "Saved Competition View",
                    savedCompetitions.getScene().getWindow());
            controller.load(savedCompetitions.getSelectionModel().getSelectedItem());
        });
    }


    // Method for returning to the main menu when the button is pressed.
    @FXML
    private void backToMainMenu(){
        // Open main menu.
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", backBtn.getScene().getWindow());
    }

}