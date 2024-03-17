package com.csia_galeta;

import com.csia_galeta.controllers.DriverQualificationAddController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.ChooseCompetitionTable;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.util.List;

/*
 Class QualificationController
 This class is the controller for the qualification window,
 describing the properties and functionality of the qualification
 */
public class QualificationController  {

    @FXML
    private ListView<Driver> listView; // Variable-reference to the list of drivers.

    @FXML
    private Button saveAndExitBtn; // Variable-reference to the exit button.

    // Method for loading data into the UI window.
    public void loadWindow(){
        // Getting the current competition.
        Competition c = CompetitionSingleton.getCurrentCompetition();

        // Taking the list of drivers from the competition.
        List<Driver> driversFromCompetition = c.getListOfDrivers();

        // Adding drivers to the UI based on random indices.
        for(int i = 0; i < driversFromCompetition.size(); i++){
            listView.getItems().addAll(driversFromCompetition.get(c.getQualification().getRandNums()[i]));
        }
    }

    /*
     Method for tracking clicks on a driver in the qualification window

     @param mouseEvent - the event triggered when clicking on a driver
     */
    @FXML
    public void handleClickOnDriver(MouseEvent mouseEvent){
        System.out.println(listView.getSelectionModel().getSelectedItem().toString());

        // Initiating the function to change points for the selected driver.
        changeQScores(listView.getSelectionModel().getSelectedItem());
    }

    /*
     Method for changing points for the selected driver

     @param d - the driver to evaluate in the qualification
     */
    @FXML
    public void changeQScores(Driver d){
        // Opening the qualification assessment window.
        DriverQualificationAddController controller = SceneOpener.openSceneAndReturnController("UserQualificationAddScore.fxml",
                "Change Driver Q Scores",
                listView.getScene().getWindow());
        controller.load(d);
    }

    // Method for saving and exiting when the back button is pressed.
    @FXML
    private void saveAndExit(){
        // Save the current competition.
        CompetitionSingleton.saveCurrentCompetition();

        // Navigate to the main screen.
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }

    // Method responsible for transitioning to the paired races window.
    @FXML
    private void proceedToRunInPairs(){
        // Getting the list of participants in the qualification
        List<Driver> drivers = CompetitionSingleton.getCurrentCompetition().getListOfDrivers();

        // Checking if everyone has been scored for all qualification races.
        for(Driver d : drivers){
            // If someone hasn't been scored.
            if(d.getLastCompletedQRound() < CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds()){
                // Throwing an error.
                Alert warn = new Alert(Alert.AlertType.ERROR);
                warn.setContentText("Fill all qualification scores for all drivers");
                warn.show();
                return; // Exit the function.
            }
        }

        // If everyone has all scores:
        // Sort the list from highest score to lowest.
        getBestAndSort(drivers);

        // Perform necessary data updates and saves.
        CompetitionSingleton.getCurrentCompetition().setQualificationOfDrivers(drivers);
        CompetitionSingleton.saveCurrentCompetition();
        ChooseCompetitionTable.prepareDriversForPairRuns(drivers);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateRunInPairsInProgress();
        CompetitionSingleton.saveCurrentCompetition();

        // Open the window of pair runs.
        P2PController controller = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Run in pairs",
                listView.getScene().getWindow());
        controller.load();
    }

    /*
     Method for sorting and printing drivers to the console based on their best scores.

     @param drivers the list of drivers to be sorted and printed
     */
    public void getBestAndSort(List<Driver> drivers) {
        System.out.println("This is a sorted array of driver's best scores: ");
        selectionSort(drivers);
        for (Driver d : drivers) {
            System.out.println(d);
        }
    }

    /*
     Method that performs sorting of drivers using the Selection Sort algorithm
     Based on the parameter of best scores in the qualification from largest to smallest.

     @param drivers - the list of drivers to be sorted
     */
    public void selectionSort(List<Driver> drivers) {
        int n = drivers.size();

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (drivers.get(j).getMaxQScore() > drivers.get(maxIndex).getMaxQScore()) {
                    maxIndex = j;
                }
            }

            Driver temp = drivers.get(maxIndex);
            drivers.set(maxIndex, drivers.get(i));
            drivers.set(i, temp);
        }
    }
}
