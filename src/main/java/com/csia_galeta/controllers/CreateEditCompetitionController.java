package com.csia_galeta.controllers;

import com.csia_galeta.Competition;
import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.Qualification;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class CreateEditCompetitionController {
    public DatePicker datePicked;
    public Label judgesLabel;
    @FXML
    private TextField competitionName;

    @FXML
    private TextField countOfCF;
    @FXML
    private Button addJudges;
    @FXML
    private Button addDrivers;

    @FXML
    private Button startQualification;

    @FXML
    private Label mainText;

    @FXML
    private ListView<Driver> listView;

    @FXML
    protected void openAddDrivers(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("Drivers.fxml",
                "Add New Driver",
                addDrivers.getScene().getWindow());
    }

    protected boolean saveCompetition(boolean notCheckWarnings){
        boolean hasWarning = false;

        if (checkCountOfQ(countOfCF.getText())){
            CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
        } else {
            hasWarning = true;
            showWarning("Please check whether entered data is a number from 1 to 5.", notCheckWarnings);
        }

        if (checkCompetitionName(competitionName.getText())){
            CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
        } else {
            hasWarning = true;
            showWarning("Please check whether entered data is a is a valid name and less or equal to 55 characters.", notCheckWarnings);
        }

        if (checkDate(datePicked.getValue())){
            CompetitionSingleton.getTmpCompetition().setCompetitionDate(datePicked.getValue());
        } else {
            hasWarning = true;
            showWarning("Date is incorrect", notCheckWarnings);
        }

        if (!hasWarning){
            System.out.println("Saving Competition...");
            return true;
        }

        return false;
    }

    @FXML
    protected void startCompetition(){
        if(!saveCompetition(false))
            return;

        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return;
        }

        CompetitionSingleton.getTmpCompetition().setCompetitionStateQualificationInProgress();
        CompetitionSingleton.addQualificationToTmpCompetition(new Qualification(CompetitionSingleton.getTmpCompetition().getListOfDrivers()));
        CompetitionSingleton.saveTmpCompetition();
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                startQualification.getScene().getWindow());
        controller.loadWindow();
    }

    @FXML
    private void saveCompetitionOnClick(){
        if(!saveCompetition(false))
            return;

        if(CompetitionSingleton.getTmpCompetition().getListOfDrivers().size() <= 3){
            showWarning("Can`t save competition with 1 player. Minimum Players: 4", false);
            return;
        }

        CompetitionSingleton.getTmpCompetition().setCompetitionStatePlanned();
        CompetitionSingleton.saveTmpCompetition();
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View",
                startQualification.getScene().getWindow());
    }

    private void showWarning(String message, boolean notShowWarn){
        if(notShowWarn)
            return;

        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show();
    }

    public void loadTmpCompetition(){
        competitionName.setText(CompetitionSingleton.getTmpCompetition().getCompetitionName());
        countOfCF.setText(CompetitionSingleton.getTmpCompetition().getAmountOfQualifyingRounds() + "");
        listView.getItems().addAll(CompetitionSingleton.getTmpCompetition().getListOfDrivers());
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    public void loadToEditCompetition(){
        CompetitionSingleton.setTmpCompetition(CompetitionSingleton.getCurrentCompetition());
        loadTmpCompetition();
        mainText.setText("Edit Competition");
        competitionName.setEditable(false);
        datePicked.setValue(CompetitionSingleton.getTmpCompetition().getCompetitionDateLocalDate());
        judgesLabel.setText(CompetitionSingleton.getTmpCompetition().getListOfJudgesString());
    }

    private boolean checkDate(LocalDate date){
        return date != null;
    }

    private boolean checkCountOfQ (String amountToBeChecked){
        if(amountToBeChecked == null && !amountToBeChecked.matches("[0-9]+"))
            return false;
        int correctAmountOFQ = Integer.parseInt(amountToBeChecked);
        return correctAmountOFQ > 0 && correctAmountOFQ <= 5;
    }

    private boolean checkCompetitionName (String nameToBeChecked){
        if(nameToBeChecked == null)
            return false;

        return nameToBeChecked.matches("^.{1,55}$");
    }

    @FXML
    public void addJudges(){
        saveCompetition(true);
        SceneOpener.openSceneAndReturnController("AddJudgeFrame.fxml",
                "Add New Judge",
                addJudges.getScene().getWindow());
    }

}