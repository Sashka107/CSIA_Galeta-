package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 The AddDriverController class is responsible for adding a driver to the competition.
 */
public class AddDriverController {

    @FXML
    private TextField driverName; // Variable - a reference to the text field for entering the name.

    @FXML
    private TextField driverSurname; // Variable - a reference to the text field for entering the surname.

    @FXML
    private TextField driverNumber; // Variable - a reference to the text field for entering the number.

    @FXML
    private TextField driverTeam; // Variable - a reference to the text field for entering the team.

    @FXML
    private Button addDriverBtn; // Variable - a reference to the button that adds a driver.


    /*
     The function is called when the "Add Driver" button is pressed
     and performs the action of adding a driver to the current competition.
     */
    @FXML
    public void addDriver(){
        Driver driver = new Driver(); // Temporary driver object for addition.
        byte nSuccess = 0; // Variable for tracking successful inputs of driver data.

        if(driver.setNameD(driverName.getText())) // Attempting to set the driver's text from the input field.
            nSuccess++; // If everything is good - increase the count of successful inputs.
        else
            showWarning("Your name is incorrect"); // Calling the function to display an error.

        if(driver.setSurnameD(driverSurname.getText())) // Similarly as above.
            nSuccess++;
        else
            showWarning("Your surname is incorrect");

        if(driver.setNumber(driverNumber.getText())) // Similarly as above.
            nSuccess++;
        else
            showWarning("Number incorrect");

        if(driver.setTeam(driverTeam.getText())) // Similarly as above.
            nSuccess++;
        else
            showWarning("Your name is incorrect");

        if(nSuccess == 4){ // If all the fields regarding the driver are entered correctly, then:
            CompetitionSingleton.addDriverToTmpCompetition(driver); // We add the driver to the current competition.
            System.out.println("Close driver window");
            Stage stage = (Stage) addDriverBtn.getScene().getWindow();
            stage.close(); // Close the driver addition window.

            // Reopen the competition creation/editing window.
            CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml", "Create New Competition");
            controller.loadTmpCompetition();
        }
    }

    /*
     Function for displaying an error in case the driver's data contains errors.

     @param message - a string with a warning depending on where the error occurs.
     */
    private void showWarning(String message){
        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show(); // Displaying an error-warning.
    }
}
