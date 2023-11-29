package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.PrevCompetitionsController;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RC_DriftController {
    public Button prevCompetitionBtn;

    @FXML
    private Button addCompetitionBtn;

    @FXML
    public void openPrevCompetitions(){
        PrevCompetitionsController controller = SceneOpener.openSceneAndReturnController("PrevCompetitions.fxml",
                "All Saved Competitions",
                prevCompetitionBtn.getScene().getWindow());
        controller.showCompetitions(CompetitionSingleton.getInstance().getCompetitions());
    }

    @FXML
    protected void openAddCompetitionWindow(){
        SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml",
                "Create New Competition",
                addCompetitionBtn.getScene().getWindow());
    }
}