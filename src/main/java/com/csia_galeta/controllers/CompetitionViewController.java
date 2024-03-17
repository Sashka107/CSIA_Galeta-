package com.csia_galeta.controllers;

import com.csia_galeta.*;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/*
 Class CompetitionViewController
 responsible for the behavior of the window displaying a previously created competition.
 */
public class CompetitionViewController {

    @FXML
    private Label competitionName; // Variable - a reference to the field for displaying the title text.
    @FXML
    private Label dateText; // Variable - a reference to the field for displaying the date text.
    @FXML
    private Label judgesNames; // Variable - a reference to the field for displaying the text of judges' names.
    @FXML
    private Label plannedStatus; // Variable - a reference to the field for displaying the text of the status.
    @FXML
    private Label qRounds; // Variable - a reference to the field for displaying the text of the round count.
    @FXML
    private ListView<Driver> listView; // Variable - a reference to the list for displaying participants.
    @FXML
    private Button playBtn; // Variable - a reference to the button to start the competition.
    @FXML
    private Button backBtn; // Variable - a reference to the button to go back to the list of created competitions.

    /*
     This method is needed to load up-to-date information about the selected competition
     into all the UI reference variables listed above.

     @param competition - the competition selected from the list of already created ones.
     */
    public void load(Competition competition){
        CompetitionSingleton.setCurrentCompetition(competition); //  Set the selected competition into the current competition.
        competitionName.setText(competition.getCompetitionName());
        qRounds.setText(competition.getAmountOfQualifyingRounds() + "");
        dateText.setText(competition.getCompetitionDate());
        judgesNames.setText(competition.getListOfJudgesString());
        plannedStatus.setText(competition.getCompetitionState());
        listView.getItems().addAll(competition.getListOfDrivers());
    }

    /*
     The method responsible for going back to the window for selecting competitions from the list of already created ones,
     triggered by pressing the back button.
     */
    @FXML
    private void back(){
        // Getting the controller of the previous scene and close the current one.
        PrevCompetitionsController controller =
                SceneOpener.openSceneAndReturnController("PrevCompetitions.fxml",
                "All Saved Competitions",
                backBtn.getScene().getWindow());

        // Open the scene
        controller.showCompetitions(CompetitionSingleton.getInstance().getCompetitions());
    }

    /*
     This method is triggered when the "Start Competition" button is pressed and opens it depending on
     the current completion status.
     */
    @FXML
    private void playCompetition(){
        // Depending on the status, different scenes are opened.
        switch (CompetitionSingleton.getCurrentCompetition().getCompetitionState()) {
            case CompetitionStates.QUALIFICATION_IN_PROGRESS -> { // Qualifications are opened.
                QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                        "Qualification",
                        playBtn.getScene().getWindow());
                controller.loadWindow();
            }
            case CompetitionStates.PLANNED -> { // Competition editing is opened.
                CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController(
                        "CreateOrEditCompetition.fxml",
                        "Edit Competition",
                        playBtn.getScene().getWindow());
                controller.loadToEditCompetition();
            }
            case CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS, CompetitionStates.FINAL_ROUND ->
            { // Pair runs are opened.
                    System.out.println("RUN IN PAIRS OPEN");

                    P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                        "Runs in pairs",
                        playBtn.getScene().getWindow());
                    controller2.load();
            }
            case CompetitionStates.COMPETITION_IS_FINISHED -> { // Final window is opened.
                FinalResultsController controller = SceneOpener.openSceneAndReturnController("FinalAfterPair.fxml", "Final Results"
                        , backBtn.getScene().getWindow());
                controller.load();
            }
            default -> { // As a precaution, handling the case if something went wrong with the states.
                Alert alertWarning = new Alert(Alert.AlertType.WARNING);
                alertWarning.setTitle("Can`t Play!");
                alertWarning.setContentText("You can`t play:\nCompetition already FINISHED");
                alertWarning.show();
            }
        }
    }
}