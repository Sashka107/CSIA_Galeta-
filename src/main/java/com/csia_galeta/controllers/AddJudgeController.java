package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 The AddJudgeController class is responsible for adding a judge to the competition.
 */
public class AddJudgeController
{
    @FXML
    private TextField judgeName; // Variable - a reference to the text field for entering the name.

    @FXML
    private TextField judgeSurname; // Variable - a reference to the text field for entering the surname.

    @FXML
    private Button addJudge; // Variable - a reference to the button that adds a judge.

    /*
     The function is called when the "Add Judge" button is pressed and performs the action of adding
     a judge to the current competition.
     */
    @FXML
    public void addJudge(){
        Judge judge = new Judge(); // Temporary judge object for addition.
        byte nSuccess = 0; // Variable for tracking successful inputs of judge data.

        if(judge.setName(judgeName.getText())) // Attempting to set the judge's text from the input field.
            nSuccess++; // If everything is good - increase the count of successful inputs.
        else
            showWarning("Your name is incorrect"); // Calling the function to display an error.

        if(judge.setSurname(judgeSurname.getText())) // Similarly as above.
            nSuccess++;
        else
            showWarning("Your surname is incorrect");

        if(nSuccess == 2){ // If all the fields regarding the judge are entered correctly, then:
            CompetitionSingleton.addJudgeToTmpCompetition(judge); // Add the judge to the current competition.
            System.out.println("Close judge window");
            Stage stage = (Stage) addJudge.getScene().getWindow();
            stage.close(); // Close the judge addition window.

            // Reopen the competition creation/editing window.
            CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml", "Create New Competition");
            controller.loadTmpCompetition();
        }
    }

    /*
     Function for displaying an error in case the judge's data contains errors.

     @param message - a string with a warning depending on where the error occurs.
     */
    private void showWarning(String message){
        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show(); // Displaying an error-warning.
    }
}