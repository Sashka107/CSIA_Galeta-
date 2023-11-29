package com.csia_galeta.controllers;

import com.csia_galeta.Competition;
import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CreateEditCompetitionController {
    @FXML
    private TextField competitionName;
    @FXML
    private TextField competitionDate;
    @FXML
    private TextField countOfCF;
    @FXML
    private Button addJudges;
    @FXML
    private Button addDrivers;

    @FXML
    private Button startQualification;

    @FXML
    private Label warnings;

    @FXML
    private Label mainText;

    @FXML
    private ListView<Driver> listView;

    @FXML
    protected void openAddDrivers(){
        saveCompetition();
        SceneOpener.openSceneAndReturnController("Drivers.fxml",
                "Add New Driver",
                addDrivers.getScene().getWindow());
    }

    private void changeUI(Driver d){
        System.out.println(d);
        listView.getItems().add(d);
    }

    protected boolean saveCompetition(){
        boolean heats = false;
        boolean name = false;

        if (checkCountOfCF(countOfCF.getText())){
            CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
            heats = true;
        } else {
            System.out.println("Please check whether entered data is a number from 1 to 127.");
            warnings.setText("Please check whether entered data is a number from 1 to 127.");
        }

        if (checkCompetitionName(competitionName.getText())){
            CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
            name = true;
        } else {
            System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
            warnings.setText("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        }

        if (heats && name){
            System.out.println("Saving Competition...");
            return true;
        }

        return false;
    }

    @FXML
    protected void startCompetition(){
        if(saveCompetition()){
            CompetitionSingleton.getTmpCompetition().setCompetitionStateQualificationInProgress();
            CompetitionSingleton.saveTmpCompetition();
            QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                    "Qualification",
                    startQualification.getScene().getWindow());
            controller.loadWindow();
        }
    }

    @FXML
    private void saveCompetitionOnClick(){
        if(saveCompetition()){
            CompetitionSingleton.getTmpCompetition().setCompetitionStatePlanned();
            CompetitionSingleton.saveTmpCompetition();
        }

        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View",
                startQualification.getScene().getWindow());
    }

    public void loadTmpCompetition(){
        competitionName.setText(CompetitionSingleton.getTmpCompetition().getCompetitionName());
        countOfCF.setText(CompetitionSingleton.getTmpCompetition().getAmountOfQualifyingRounds() + "");
        listView.getItems().addAll(CompetitionSingleton.getTmpCompetition().getListOfDrivers());
    }

    public void loadToEditCompetition(){
        CompetitionSingleton.setTmpCompetition(CompetitionSingleton.getCurrentCompetition());
        loadTmpCompetition();
        mainText.setText("Edit Competition");
        competitionName.setEditable(false);
    }

    private boolean checkCountOfCF (String amountToBeChecked){
        if(amountToBeChecked == null)
            return false;

        return amountToBeChecked.matches("^\\b([1-9]|[1-9][0-9]|1[01][0-9]|12[0-7])\\b$");
    }

    private boolean checkCompetitionName (String nameToBeChecked){
        if(nameToBeChecked == null)
            return false;

        return nameToBeChecked.matches("^.{1,55}$");
    }

    @FXML
    public void addJudges(){
        saveCompetition();
        SceneOpener.openSceneAndReturnController("AddJudgeFrame.fxml",
                "Add New Judge",
                addJudges.getScene().getWindow());
    }

    public void handleClear(MouseEvent mouseEvent) {
        warnings.setText("");
    }

}