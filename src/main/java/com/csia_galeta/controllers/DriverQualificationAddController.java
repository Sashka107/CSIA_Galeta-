package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class DriverQualificationAddController {

    @FXML
    private TextField driverScore;

    @FXML
    private Label driverInfo;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Button saveBtn;

    @FXML
    private Button exitBtn;


    private Driver editDriver;
    private int roundSelected = -1;

    public void load(Driver d){

        editDriver = d;
        driverInfo.setText(editDriver.toString());

        int n = CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds();

        List<MenuItem> items = new ArrayList<>();

        for(int i = 1; i <= n; i++){
            MenuItem item = new MenuItem(i + "");

            items.add(item);
            item.setOnAction(event -> selectRound(event));
        }

        menuButton.getItems().addAll(items);
    }

    private void selectRound(ActionEvent event){

        MenuItem n = (MenuItem) event.getSource();
        roundSelected = Integer.parseInt(n.getText());

        if(roundSelected < editDriver.getLastCompletedQRound() + 1 && editDriver.getQualificationScore().get(roundSelected-1) != null){
            driverScore.setText(editDriver.getQualificationScore().get(roundSelected-1) + "");
            driverScore.setEditable(false);
            saveBtn.setDisable(true);
        }else if(roundSelected != editDriver.getLastCompletedQRound() + 1){
            saveBtn.setDisable(false);
            roundSelected = editDriver.getLastCompletedQRound() + 1;
        }

        menuButton.setText("Round #" + roundSelected);

        try{
            editDriver.getQualificationScore().get(roundSelected-1);
        }catch (IndexOutOfBoundsException ex){
            driverScore.setText("");
            driverScore.setEditable(true);
            saveBtn.setDisable(false);
        }
    }

    @FXML
    private void saveForRound(){
        if(roundSelected == -1){
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Round not Selected");
            alertWarning.setContentText("ROUND NOT SELECTED - please select it");
            alertWarning.show();
            return;
        }

        editDriver.addDriverScoreForQRound(Integer.parseInt(driverScore.getText()));
        CompetitionSingleton.saveCurrentCompetition();
        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
        alertSuccess.setTitle("Round Score Saved");
        alertSuccess.setContentText("SUCCESSFULLY SAVED SCORES FOR ROUND: " + roundSelected);
        alertSuccess.show();
        driverScore.setEditable(false);
        saveBtn.setDisable(true);
    }


    @FXML
    private void exitToQList() {
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                exitBtn.getScene().getWindow());

        controller.loadWindow();
    }
}