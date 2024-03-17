package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.PrevCompetitionsController;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/*
 Class RC_DriftController responsible for the behavior of the main window of the program.
 */
public class RC_DriftController {
    public Button prevCompetitionBtn; // Variable - a reference to the button for previous/already created competitions.

    @FXML
    private Button addCompetitionBtn; // Variable - a reference to the button that adds a competition.

    /*
     Method triggered when the "Previous Competitions" button is pressed.
     Opens the window with a list of all previously created and previous competitions.
     */
    @FXML
    public void openPrevCompetitions(){
        PrevCompetitionsController controller = SceneOpener.openSceneAndReturnController("PrevCompetitions.fxml",
                "All Saved Competitions",
                prevCompetitionBtn.getScene().getWindow());
        controller.showCompetitions(CompetitionSingleton.getInstance().getCompetitions());
    }

    /*
     Method triggered when the "Add New Competition" button is pressed.
     Opens the window for creating a new competition.
     */
    @FXML
    protected void openAddCompetitionWindow(){
        SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml",
                "Create New Competition",
                addCompetitionBtn.getScene().getWindow());
    }
}