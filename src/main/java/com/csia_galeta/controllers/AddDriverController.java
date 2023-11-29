package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddDriverController {

    @FXML
    private TextField driverName;

    @FXML
    private TextField driverSurname;

    @FXML
    private TextField driverNumber;

    @FXML
    private TextField driverTeam;

    @FXML
    private Button addDriverBtn;

    @FXML
    public void addDriver(){
        Driver driver = new Driver();
        byte nSuccess = 0;

        if(driver.setNameD(driverName.getText()))
            nSuccess++;
        else
            showWarning(driverName, "Your name is incorrect");

        if(driver.setSurnameD(driverSurname.getText()))
            nSuccess++;
        else
            showWarning(driverSurname, "Your surname is incorrect");

        if(driver.setNumber(driverNumber.getText()))
            nSuccess++;
        else
            showWarning(driverNumber, "Number incorrect");

        if(driver.setTeam(driverTeam.getText()))
            nSuccess++;
        else
            showWarning(driverTeam, "Your name is incorrect");

        if(nSuccess == 4){
            CompetitionSingleton.addDriverToTmpCompetition(driver);
            System.out.println("Close driver window");
            Stage stage = (Stage) addDriverBtn.getScene().getWindow();
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
        driverTeam.setStyle("-fx-text-inner-color: black;");
        driverNumber.setStyle("-fx-text-inner-color: black;");
        driverSurname.setStyle("-fx-text-inner-color: black;");
        driverName.setStyle("-fx-text-inner-color: black;");
    }

    public void handleClear(MouseEvent mouseEvent) {
        resetStyles();
    }
}
