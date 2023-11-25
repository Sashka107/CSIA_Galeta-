package com.csia_galeta;

import com.csia_galeta.people.Driver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Label warnings;

    @FXML
    private ListView<String> listView;

    @FXML
    protected void openAddDrivers(){
        System.out.println("Click");
        /* Stage stage = (Stage) addJudges.getScene().getWindow();
        stage.close(); */
        CompetitionSingleton.driverAddHandler(this::changeUI);
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

    private void changeUI(Driver d){
        System.out.println(d);
        listView.getItems().add(d.toString());
    }

    @FXML
    protected void saveCompetition(){
        boolean heats = false;
        boolean name = false;
        if (checkCountOfCF(countOfCF.getText()) && countOfCF.getText() != null){
            CompetitionSingleton.addCountOfRounds(Integer.parseInt(countOfCF.getText()));
            heats = true;
        } else {
            System.out.println("Please check whether entered data is a number from 1 to 127.");
            warnings.setText("Please check whether entered data is a number from 1 to 127.");
        }

        if (checkCompetitionName(competitionName.getText()) && competitionName.getText() != null){
            CompetitionSingleton.addToTmpCompetitionName(competitionName.getText());
            name = true;
        } else {
            System.out.println("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
            warnings.setText("Please check whether entered data is a is a valid name and less or equal to 55 characters.");
        }

        if (heats && name){
            System.out.println("Saving Competition...");
            CompetitionSingleton.saveTmpCompetition();
        }
    }

    private boolean checkCountOfCF (String amountToBeChecked){
        return amountToBeChecked.matches("^\\b([1-9]|[1-9][0-9]|1[01][0-9]|12[0-7])\\b$");
    }

    private boolean checkCompetitionName (String nameToBeChecked){
        return nameToBeChecked.matches("^.{1,55}$");
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

    public void handleClear(MouseEvent mouseEvent) {
        warnings.setText("");
    }

}