package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

/*
 Class FinalResultsController
 This class serves as the controller for the final competition results window,
 describing and containing functionality for displaying the competition results.
 */
public class FinalResultsController {
    public Label top1; // Variable - reference to the field for displaying the first place.
    public Label top2; // Variable - reference to the field for displaying the second place.
    public Label top3; // Variable - reference to the field for displaying the third place.
    public Label top4; // Variable - reference to the field for displaying the fourth place.

    public ListView<String> qDriversList; // Variable - reference to the list for displaying the qualification.


    // Method for loading information into all fields on the UI.
    public void load(){
        // Obtain the list of winners, i.e., the last four drivers in the order of victory places.
        List<Driver> winners =  CompetitionSingleton.getCurrentCompetition().getListOfDrivers();

        // Set values to the fields on the UI.
        top1.setText(winners.get(0).toStringWinner());
        top2.setText(winners.get(1).toStringWinner());
        top3.setText(winners.get(2).toStringWinner());
        top4.setText(winners.get(3).toStringWinner());

        // Load the list of drivers from the qualification with the best result.
        for(var driver : CompetitionSingleton.getCurrentCompetition().getQualificationOfDrivers()){
            qDriversList.getItems().add(driver.toStringSimple() + " best q score: " + driver.getMaxQScore());
        }
    }

    /*
     Method for returning to the main menu, triggered when the "Exit" button is clicked.

     @param actionEvent - the button click event
     */
    public void toMainMenu(ActionEvent actionEvent) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", top1.getScene().getWindow());
    }
}