package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddJudgeController
{

    @FXML
    private TextField judgeName;

    @FXML
    private TextField judgeSurname;


    @FXML
    private Button addJudge;


    @FXML
    public void addJudge(){
        Judge judge = new Judge();
        byte nSuccess = 0;

        if(judge.setName(judgeName.getText()))
            nSuccess++;
        else
            showWarning(judgeName, "Your name is incorrect");

        if(judge.setSurname(judgeSurname.getText()))
            nSuccess++;
        else
            showWarning(judgeSurname, "Your surname is incorrect");

        if(nSuccess == 2){
            CompetitionSingleton.addJudgeToTmpCompetition(judge);
            System.out.println("Close judge window");
            Stage stage = (Stage) addJudge.getScene().getWindow();
            stage.close();
            resetStyles();

            CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml", "Create New Competition");
            controller.loadTmpCompetition();
        }
    }

    private void showWarning(TextField textField, String message){
        textField.setText(message);
        textField.setStyle("-fx-text-inner-color: red;");
    }

    private void resetStyles(){
        judgeName.setStyle("-fx-text-inner-color: black;");
        judgeSurname.setStyle("-fx-text-inner-color: black;");
    }

    public void handleClear(MouseEvent mouseEvent) {
        resetStyles();
    }

}
