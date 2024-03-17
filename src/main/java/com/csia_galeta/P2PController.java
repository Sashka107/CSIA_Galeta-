package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Pair;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/*
 Class P2PController
 This class serves as the controller for the pair runs window and contains the logic and functionality
 for switching between tournament grids and the runs in pair process until the competition is completed.
 */
public class P2PController {

    public Button saveAndExitBtn; // Variable - reference to the exit button.
    public Button nextNet; // Variable - reference to the button for proceeding to the next stage.
    public Label windowTitle; // Variable - reference to the field displaying the window title.
    @FXML
    private ListView<Pair> pairListView = new ListView<>(); // Variable - reference to the list of pairs.

    /*
     Method triggered when a pair is clicked from the list.
     Opens the pair for evaluation, etc.

     @param mouseEvent - the mouse click event on the pair
     */
    @FXML
    public void handleClickOnPair(MouseEvent mouseEvent){

        // Obtain the pair object that was clicked.
        Pair p = pairListView.getSelectionModel().getSelectedItem();

        // If pair is not empty.
        if(p != null){
            if(p.getWinner() != null){ // If the winner exists.
                // Display a warning and exit the method.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This pair already get scores");
                alert.setHeaderText("Pair can`t be edited");
                alert.show();
                return;
            }

            // If there is no winner yet, open the evaluation of the pair.
            editPair(p);
        }
    }


    // Method for loading information for head-to-head races into the UI window.
    public void load(){
        // If the competition is in the final round:
        if(CompetitionSingleton.getCurrentCompetition().getCompetitionState()
                .equals(CompetitionStates.FINAL_ROUND)){

            // Set all necessary UI fields to the required text.
            windowTitle.setText("Final");
            saveAndExitBtn.setDisable(true); // Disable the exit button and prohibit exiting.
            nextNet.setText("Finish competition");

            // Set a different function execution for the "Next Stage" button.
            nextNet.setOnAction(event -> finish());

            // Load and set the list of all pairs in the UI.
            pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
            return;
        }

        // If the list of pairs has not been created or is empty:
        if(CompetitionSingleton.getCurrentCompetition().getListOfPairs() == null ||
                CompetitionSingleton.getCurrentCompetition().getListOfPairs().isEmpty()){

            // Then create the list of pairs.
            CompetitionSingleton.getCurrentCompetition().setListOfPairs(Pair.createPairs(CompetitionSingleton.getCurrentCompetition().getListOfDrivers()));

            // And save it to a JSON file along with the competition.
            CompetitionSingleton.saveCurrentCompetition();
        }

        // Load and set the list of all pairs in the UI.
        pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
    }


    // Method for advancing to the next stage of head-to-head races.
    public void toNextNet(){
        System.out.println("Next net activate");

        // Check if all pairs have been evaluated.
        if(!checkAllWinners()){
            // If not evaluated, display a warning.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return; // Exit the method.
        }

        // If there are 4 participants left, start the final game.
        if(CompetitionSingleton.getCurrentCompetition().getListOfDrivers().size() == 4){
            finalPlay();
            return; // Exit from method.
        }

        // Create the next pair race grid.
        generateNextNet();
    }


    // Method for final head-to-head races in the competition.
    private void finalPlay(){
        System.out.println("final play");

        // Form pairs for the final and the race for 3rd place.
        Pair winners = new Pair();
        winners.setPairNum(1);
        Pair losers = new Pair();
        losers.setPairNum(2);

        // Winners into the final pair and losers into the 3rd place pair from the semifinal pairs.
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winners.addDriver(pair.winner);
            if(pair.p1.equals(pair.winner)){
                losers.addDriver(pair.p2);
            }else {
                losers.addDriver(pair.p1);
            }
        }

        // Create a new list of pairs for the final.
        List<Pair> newPairs = new ArrayList<>(2);
        newPairs.add(winners);
        newPairs.add(losers);

        // Replace the previous list of pairs with the final list, then save everything.
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(newPairs);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFinalRound();
        CompetitionSingleton.saveCurrentCompetition();

        // Set all necessary UI fields to the required text.
        windowTitle.setText("Final");
        saveAndExitBtn.setDisable(true);
        nextNet.setText("Finish competition");

        // Set a different function execution for the "Next Stage" button.
        nextNet.setOnAction(event -> finish());

        // Reload the information about pairs into the window, now for the final.
        load();
    }


    // Method for generating the next grid of head-to-head races.
    private void generateNextNet(){
        System.out.println("Generating next net");

        List<Driver> newDrivers = new ArrayList<>();

        // Form pairs from the winners of the previous grid.
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() != null)
                newDrivers.add(pair.getWinner());
            else
                throw new NullPointerException("Winner in pair is null to create next net drivers list");
        }

        // Perform necessary saving and updates in the participants list.
        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(newDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.saveCurrentCompetition();

        // Reload the information about pairs into the window.
        load();
    }

    /*
     Method is needed to check if all pairs have been evaluated.

     @return true if all pairs have been evaluated, false if at least one pair is not evaluated
     */
    private boolean checkAllWinners(){

        // Iterate through all pairs and check the winner.
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() == null) // If there is no winner.
                return false; // Return false.
        }

        return true;
    }

    /*
     Method triggered when the "finish" button is clicked.
     Forms the list of winners and opens the results window.
     */
    private void finish(){
        System.out.println("Finish competition");

        // Check that all pairs have not been evaluated.
        if(!checkAllWinners()){
            // If not all pairs are evaluated, warn about it.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return; // Exit from method.
        }

        // If all pairs are evaluated above, create a final list of winners for 4 participants.
        List<Driver> winnersDrivers = new ArrayList<>(4);

        // Iterate through the list of pairs and form the winners.
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winnersDrivers.add(pair.getWinner());
            if(pair.getP1().equals(pair.winner)){
                winnersDrivers.add(pair.getP2());
            }else {
                winnersDrivers.add(pair.getP1());
            }
        }

        // Perform saving and updating in the competition.
        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(winnersDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFullDone();
        CompetitionSingleton.saveCurrentCompetition();

        // Open the  final results window.
        FinalResultsController controller = SceneOpener.openSceneAndReturnController("FinalAfterPair.fxml", "Final Results", pairListView.getScene().getWindow());
        controller.load();
    }

    /*
     Method for editing/evaluating a pair.

     @param pair the pair to be edited
     */
    private void editPair(Pair pair){

        // Open the pair editing window.
        PairEditController controller = SceneOpener.openSceneAndReturnController("PairEdit.fxml", "Pair Edit", pairListView.getScene().getWindow());
        controller.load(pair);
    }

    /*
     Method for saving and exiting the head-to-head races window.

     @param actionEvent the button click event
     */
    public void saveAndExit(ActionEvent actionEvent) {
        // Save current competition.
        CompetitionSingleton.saveCurrentCompetition();

        // Open main menu.
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }
}
