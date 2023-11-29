package com.csia_galeta.controllers;

import com.csia_galeta.Competition;
import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class CompetitionViewController {

    @FXML
    private Label competitionName;
    @FXML
    private Label dateText;
    @FXML
    private Label judgesNames;
    @FXML
    private Label plannedStatus;
    @FXML
    private Label qRounds;

    @FXML
    private ListView<Driver> listView;

    @FXML
    private Button playBtn;
    @FXML
    private Button editBtn;

    public void load(Competition competition){
        CompetitionSingleton.setCurrentCompetition(competition);
        competitionName.setText(competition.getCompetitionName());
        qRounds.setText(competition.getAmountOfQualifyingRounds() + "");
        dateText.setText("Test date");
        judgesNames.setText(competition.getListOfJudges().toString());
        plannedStatus.setText(competition.getCompetitionState());
        listView.getItems().addAll(competition.getListOfDrivers());
    }

    @FXML
    private void editCompetition(){
        if(!CompetitionSingleton.getCurrentCompetition().getCompetitionState().equals(CompetitionStates.PLANNED)){
            Alert alertWarning = new Alert(Alert.AlertType.ERROR);
            alertWarning.setTitle("Can`t Edit!");
            alertWarning.setContentText("You can`t edit! Cause: Competition has a state:\n"
                    + CompetitionSingleton.getCurrentCompetition().getCompetitionState().toUpperCase() +
                    ", for editing it it must be: "
                    + CompetitionStates.PLANNED);
            alertWarning.show();
            return;
        }

        CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController(
                "CreateOrEditCompetition.fxml",
                "Edit Competition",
                editBtn.getScene().getWindow());
        controller.loadToEditCompetition();
    }

    @FXML
    private void playCompetition(){
        switch (CompetitionSingleton.getCurrentCompetition().getCompetitionState()) {
            case CompetitionStates.PLANNED, CompetitionStates.QUALIFICATION_IN_PROGRESS -> {
                QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                        "Qualification",
                        playBtn.getScene().getWindow());
                controller.loadWindow();
            }
            case CompetitionStates.QUALIFICATION_DONE, CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS ->{
                    System.out.println("RUN IN PAIRS OPEN");

                /*QualificationController controller2 = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                        "Qualification",
                        playBtn.getScene().getWindow());
                controller.loadWindow();*/
            }
            default -> {
                Alert alertWarning = new Alert(Alert.AlertType.WARNING);
                alertWarning.setTitle("Can`t Play!");
                alertWarning.setContentText("You can`t play:\nCompetition already FINISHED");
                alertWarning.show();
            }
        }
    }

}