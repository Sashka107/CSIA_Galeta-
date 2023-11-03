package com.csia_galeta;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller2 {
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
    private Label competitionJudges;

    @FXML
    protected void openAddDrivers(){
        System.out.println("Click");
        /* Stage stage = (Stage) addJudges.getScene().getWindow();
        stage.close(); */
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Drivers.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle("Add drivers");
        newStage.setScene(scene);
        newStage.show();
    }

    @FXML
    protected void saveCompetition(){
        System.out.println("Saving Competition...");
        CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
        CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
        // date
        CompetitionSingleton.saveTmpCompetition();
    }

    @FXML
    public void addJudges(){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("AddJudgeFrame.fxml"));
        Scene scene = null;
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();
        newStage.setTitle("Frame 3!");
        newStage.setScene(scene);
        newStage.show();
    }

}