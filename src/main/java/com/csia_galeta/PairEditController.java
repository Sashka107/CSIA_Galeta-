package com.csia_galeta;

import com.csia_galeta.people.Pair;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;


/*
 Class PairEditController
 This class serves as the controller for the pair evaluation window in races.
 It describes the logic of evaluation during races and also DeathMatch.
 */
public class PairEditController {

    public TextField p1ScoreRace1; // Variable - reference to the field for evaluating the first player in the first race.
    public TextField p2ScoreRace1; // Variable - reference to the field for evaluating the second player in the first race.
    public TextField p2ScoreRace2; // Variable - reference to the field for evaluating the second player in the second race.
    public TextField p1ScoreRace2; // Variable - reference to the field for evaluating the first player in the second race.
    public MenuButton deathMatch; // Variable - reference to the DeathMatch menu button.
    public Label roundText; // Variable - reference to the field for the current race of the pair.
    public Button NextOrSaveBtn; // Variable - reference to the "Save or Next Race" button.
    public Label d1Label; // Variable - reference to the field for information about the first player in the first race.
    public Label d2Label; // Variable - reference to the field for information about the second player in the first race.
    public Label d2Label2; // Variable - reference to the field for information about the second player in the second race.
    public Label d1Label2; // Variable - reference to the field for information about the first player in the second race.
    private int p1Score; // Score of the first driver.
    private int p2Score; // Score of the second driver.

    private int raceCount = 0; // Number of rounds within a pair.
    private int roundCount = 1; // Number of races.

    private Pair currentPair; // Pair which is being assessed.

    /*
     Method for loading information into the UI window about the pair.

     @param p the pair for races and evaluation
     */
    public void load(Pair p){

        // Saving into a variable.
        currentPair = p;

        // Set text in text fields and disable unnecessary buttons.
        d1Label.setText(p.p1.toStringPairEdit());
        d1Label2.setText(p.p1.toStringPairEdit());
        d2Label.setText(p.p2.toStringPairEdit());
        d2Label2.setText(p.p2.toStringPairEdit());
        deathMatch.setDisable(true);
        NextOrSaveBtn.setDisable(true);
    }

     // Method sets points for the first race.
    public void setScoreRace1(){

        // If the points are set incorrectly.
        if(!checkRaceScores(p1ScoreRace1) && !checkRaceScores(p2ScoreRace1)){
            // Display a warning.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Incorrect scores. Please provide from 0 to 10 and press enter");
            alert.show();
            return; // Exit from the function.
        }

        // If the first player's points field in the first race is not empty:
        if(!p1ScoreRace1.getText().isEmpty()){
            // Then convert the text from the field and set it to the variable for points.
            int p1Score = Integer.parseInt(p1ScoreRace1.getText());
            // Set the remainder from the points to the second player, calculated as: 10 - points of the first player.
            p2ScoreRace1.setText(10 - p1Score + "");

            // Adding up the total points for the race for both participants.
            this.p1Score += p1Score;
            this.p2Score += 10 - p1Score;
        }else if(!p2ScoreRace1.getText().isEmpty()){ // Doing the same thing but for the participants in reverse
            int p2Score = Integer.parseInt(p2ScoreRace1.getText());
            p1ScoreRace1.setText(10 - p2Score + "");
            this.p2Score += p2Score;
            this.p1Score += 10 - p2Score;
        }

        // Switching off unnecessary buttons.
        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        raceCount++; // Increasing the amount of races.
        NextOrSaveBtn.setDisable(true);

        // Check whether the round is finished.
        checkForRound();
    }

    /*
     Method for setting points for the second race
     This method is completely analogous to the one above, but it does it for the second race
     */
    public void setScoreRace2(){
        if(!checkRaceScores(p1ScoreRace2) && !checkRaceScores(p2ScoreRace2)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Incorrect scores. Please provide from 0 to 10 and press enter");
            alert.show();
            return;
        }

        if(!p1ScoreRace2.getText().isEmpty()){
            int p1Score = Integer.parseInt(p1ScoreRace2.getText());
            p2ScoreRace2.setText(10 - p1Score + "");
            this.p1Score += p1Score;
            this.p2Score += 10 - p1Score;
        }else if(!p2ScoreRace2.getText().isEmpty()){
            int p2Score = Integer.parseInt(p2ScoreRace2.getText());
            p1ScoreRace2.setText(10 - p2Score + "");
            this.p2Score += p2Score;
            this.p1Score += 10 - p2Score;
        }

        p1ScoreRace2.setDisable(true);
        p2ScoreRace2.setDisable(true);
        raceCount++;
        NextOrSaveBtn.setDisable(true);
        checkForRound();
    }

    /*
     Method for checking the correctness of entering race points for a participant

     @return true if the points are correct, false otherwise
     */
    private boolean checkRaceScores(TextField field){

        // Checking if the field is empty and if it contains anything other than digits.
        if(field.getText().isEmpty() || !field.getText().matches("[0-9]+"))
            return false; // If yes - false

        // If the above conditions are met, convert the number.
        int score = Integer.parseInt(field.getText());
        return score >= 0 && score <= 10; // And check if the number is within the range from 0 to 10.
    }


    // Method for checking how many rounds have already been played by a pair.
    public void checkForRound(){

        // If the number of rounds is less than 2, then exit the function.
        if (raceCount != 2)
            return;

        System.out.println("P1 score = " + p1Score);
        System.out.println("P2 score = " + p2Score);

        // If there are 3 rounds and the points are equal for both:
        if(roundCount == 3 && p1Score == p2Score){
            // Then initiate a Death-Match
            NextOrSaveBtn.setText("Death-Match");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> deathMatch());
        } else if (p1Score == p2Score){ // If the points are equal in the round:
            // Then continue the races as OMT (One More Time).
            NextOrSaveBtn.setText("OMT");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> clearWindow());
        } else {
            // Otherwise, save the results, which means that someone
            // has more points and someone has fewer.
            NextOrSaveBtn.setText("Save pair results");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> saveAndExit());
        }
    }

    // Method for Death-match initiation.
    private void deathMatch(){

        // Disable unnecessary buttons.
        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        p1ScoreRace2.setDisable(true);
        p2ScoreRace2.setDisable(true);

        // Clear all points from the fields.
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");

        // Reset all variables to zero.
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;

        // Set the title.
        roundText.setText("Death-match");

        List<MenuItem> items = new ArrayList<>();

        // Formulate the selection of the winner in the menu button.
        for(int i = 0; i < 2; i++){
            MenuItem item = new MenuItem("Player " + (i+1));
            item.setOnAction(event -> setWinnerDeathMatch(event));
            items.add(item);
        }

        // Add menu items for determining the winner.
        deathMatch.getItems().addAll(items);

        // Enabling and disabling buttons.
        deathMatch.setDisable(false);
        NextOrSaveBtn.setDisable(true);
    }



    // Method for saving and exiting the assessment window for pair participants.
    private void saveAndExit(){

        // Check who won - the one who won is set as the winner of the pair.
        if(p1Score > p2Score) {
            currentPair.setWinner(currentPair.getP1());
        } else {
            currentPair.setWinner(currentPair.getP2());
        }

        // Return to the window with all pairs.
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    /*
     Method for setting the winner in a Death-Match

     @param event the event when clicking on the button
     */
    private void setWinnerDeathMatch(ActionEvent event){

        // Retrieve the item from the menu button from the click event.
        MenuItem n = (MenuItem) event.getSource();

        // Check who was selected and set them as the winner.
        if(n.getText().equals("Player 1"))
            currentPair.setWinner(currentPair.getP1());
        else
            currentPair.setWinner(currentPair.getP2());

        deathMatch.setText(n.getText());

        // Set text in the button and enable clicking.
        NextOrSaveBtn.setText("Save and exit");
        NextOrSaveBtn.setDisable(false);
        NextOrSaveBtn.setOnAction(actionEvent -> saveAndExitAfterDeathMatch());
    }

    // Method for saving and exiting after a Death-Match.
    private void saveAndExitAfterDeathMatch(){
        // Return to the window with pairs.
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    // Method for clearing the pair assessment window, used between OMT pair races.
    private void clearWindow(){
        // Enable the necessary fields for entering points.
        p1ScoreRace1.setDisable(false);
        p2ScoreRace1.setDisable(false);
        p1ScoreRace2.setDisable(false);
        p2ScoreRace2.setDisable(false);

        // Remove the text from the score fields.
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");

        // Reset variables to zero.
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;

        // Changing text and disabling button.
        roundText.setText("Round: " + roundCount);
        NextOrSaveBtn.setDisable(true);
    }
    
}