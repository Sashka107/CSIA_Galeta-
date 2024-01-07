package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.util.List;

public class FinalResultsController {
    public Label top1;
    public Label top2;
    public Label top3;
    public Label top4;

    public void load(){
        List<Driver> winners =  CompetitionSingleton.getCurrentCompetition().getListOfDrivers();
        top1.setText(winners.get(0).toStringWinner());
        top2.setText(winners.get(1).toStringWinner());
        top3.setText(winners.get(2).toStringWinner());
        top4.setText(winners.get(3).toStringWinner());
    }

    public void toMainMenu(ActionEvent actionEvent) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", top1.getScene().getWindow());
    }
}