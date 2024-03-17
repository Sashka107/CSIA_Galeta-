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

/*
 Class DriverQualificationAddController
 Responsible for the behavior of the window for adding qualification points for a driver.
 */
public class DriverQualificationAddController {

    @FXML
    private TextField driverScore; // Variable - a reference to the field for entering points.
    @FXML
    private Label driverInfo; // Variable - a reference to the field for displaying information about the driver.
    @FXML
    private MenuButton menuButton; // Variable - a reference to the button for selecting a round.
    @FXML
    private Button exitBtn; // Variable - a reference to the button for exiting the window.
    private Driver editDriver; // Current driver for editing.
    private int roundSelected = -1; // Default selected round.
    private boolean isAssessed = false; // State if the driver has been previously evaluated.

    /*
     Method for loading the editing of qualification points for a driver.

     @param d - the driver for editing.
     */
    public void load(Driver d){

        editDriver = d; // Assigning the parameter to an internal variable.
        driverInfo.setText(editDriver.toString()); // Displaying information about the driver.

        // Obtaining the number of qualification rounds for the current competition.
        int n = CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds();

        List<MenuItem> items = new ArrayList<>();

        // Loading the number of rounds into the round selection menu button.
        for(int i = 1; i <= n; i++){
            MenuItem item = new MenuItem(i + "");
            items.add(item);

            // Setting the method for handling button click.
            item.setOnAction(event -> selectRound(event));
        }

        // Adding all round buttons to the round selection menu.
        menuButton.getItems().addAll(items);
    }

    /*
     Method for handling a click on a round from the menu button.

     @param event - the click event, created by the JavaFX system.
     */
    private void selectRound(ActionEvent event){

        // Obtaining the object from the event on which the click occurred.
        MenuItem n = (MenuItem) event.getSource();

        // Through the text of the round, the number of the selected round is extracted.
        roundSelected = Integer.parseInt(n.getText());

        // Check whether the round has already been filled, and if so, we display information about the points.
        if(roundSelected < editDriver.getLastCompletedQRound() + 1 && editDriver.getQualificationScore().get(roundSelected-1) != null){
            driverScore.setText(editDriver.getQualificationScore().get(roundSelected-1) + "");
            driverScore.setEditable(false);
            isAssessed = true;
        }
        // Performing checks to ensure that rounds are filled one after the other.
        else if(roundSelected != editDriver.getLastCompletedQRound() + 1){
            roundSelected = editDriver.getLastCompletedQRound() + 1;
            isAssessed = false;
        }

        // Displaying on the button which round was selected from the menu list.
        menuButton.setText("Round #" + roundSelected);

        // Attempting to retrieve the round by index from the driver.
        try{
            editDriver.getQualificationScore().get(roundSelected-1);
        }catch (IndexOutOfBoundsException ex){ // If there is no such round, allow for editing points for this round.
            driverScore.setText("");
            driverScore.setEditable(true);
        }
    }

    /*
     Method that saves the qualification points for the round.

     @return true - if everything went well, false - otherwise.
     */
    @FXML
    private boolean saveForRound(){
        // If the round was not selected, a warning window is displayed.
        if(roundSelected == -1){
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Round not Selected");
            alertWarning.setContentText("ROUND NOT SELECTED - please select it");
            alertWarning.show();
            return false;
        }

        // If the entered points are empty or do not meet the restrictions, a warning window is displayed.
        if(driverScore.getText().isEmpty() || !driverScore.getText().matches("\\b(?:[0-9]|[1-9][0-9]|100)\\b")){
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Incorrect value");
            alertWarning.setContentText("Provide value from 0 to 100");
            alertWarning.show();
            return false;
        }

        // If the driver has already been evaluated.
        if (!isAssessed){
            editDriver.addDriverScoreForQRound(Integer.parseInt(driverScore.getText()));
        }

        // Save the current competition.
        CompetitionSingleton.saveCurrentCompetition();
        driverScore.setEditable(false);
        return true;
    }

    /*
     Method responsible for exiting the editing of one driver to the qualification list.
     */
    @FXML
    private void exitToQList() {

        // If unable to save the points for the round.
        if(!saveForRound())
            return; // We exit the function and prevent exiting to the qualification list.

        // If everything is good, we exit to the qualification list.
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                exitBtn.getScene().getWindow());
        controller.loadWindow();
    }
}