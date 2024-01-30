package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Pair;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class PairEditController {

    public TextField p1ScoreRace1;
    public TextField p2ScoreRace1;
    public TextField p2ScoreRace2;
    public TextField p1ScoreRace2;
    public MenuButton deathMatch; //
    public Button deathMatchWonButton; //
    public Label roundText;
    public Button NextOrSaveBtn;
    public Label d1Label;
    public Label d2Label;
    public Label d2Label2;
    public Label d1Label2;

    private int p1Score;
    private int p2Score;

    private int raceCount = 0;
    private int roundCount = 1;

    private Pair currentPair;

    public void load(Pair p){
        currentPair = p;

        d1Label.setText(p.p1.toStringPairEdit());
        d1Label2.setText(p.p1.toStringPairEdit());
        d2Label.setText(p.p2.toStringPairEdit());
        d2Label2.setText(p.p2.toStringPairEdit());
        deathMatch.setDisable(true);
        NextOrSaveBtn.setDisable(true);
    }

    public void setScoreRace1(){
        if(!checkRaceScores(p1ScoreRace1) && !checkRaceScores(p2ScoreRace1)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Incorrect scores. Please provide from 0 to 10 and press enter");
            alert.show();
            return;
        }

        if(!p1ScoreRace1.getText().isEmpty()){
            int p1Score = Integer.parseInt(p1ScoreRace1.getText());
            p2ScoreRace1.setText(10 - p1Score + "");
            this.p1Score += p1Score;
            this.p2Score += 10 - p1Score;
        }else if(!p2ScoreRace1.getText().isEmpty()){
            int p2Score = Integer.parseInt(p2ScoreRace1.getText());
            p1ScoreRace1.setText(10 - p2Score + "");
            this.p2Score += p2Score;
            this.p1Score += 10 - p2Score;
        }

        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        raceCount++;
        NextOrSaveBtn.setDisable(true);
        checkForRound();
    }

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

    private boolean checkRaceScores(TextField field){
        if(field.getText().isEmpty() || !field.getText().matches("[0-9]+"))
            return false;

        int score = Integer.parseInt(field.getText());
        return score >= 0 && score <= 10;
    }

    public void checkForRound(){
        if (raceCount != 2)
            return;

        System.out.println("P1 score = " + p1Score);
        System.out.println("P2 score = " + p2Score);

        if(roundCount == 3 && p1Score == p2Score){
            NextOrSaveBtn.setText("Death-Match");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> deathMatch());
        } else if (p1Score == p2Score){
            NextOrSaveBtn.setText("OMT");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> clearWindow());
        } else {
            NextOrSaveBtn.setText("Save pair results");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> saveAndExit());
        }
    }

    private void deathMatch(){
        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        p1ScoreRace2.setDisable(true);
        p2ScoreRace2.setDisable(true);
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;
        roundText.setText("Death-match");

        List<MenuItem> items = new ArrayList<>();

        MenuItem item1 = new MenuItem("P" + 1 + ": " + currentPair.getP1().toStringSimple());
        MenuItem item2 = new MenuItem("P" + 2 + ": " + currentPair.getP2().toStringSimple());
        item1.setOnAction(this::setWinnerDeathMetch);
        item2.setOnAction(this::setWinnerDeathMetch);
        items.add(item1);
        items.add(item2);

        deathMatch.getItems().addAll(items);
        deathMatch.setDisable(false);
        NextOrSaveBtn.setDisable(true);
    }

    private void saveAndExit(){
        if(p1Score > p2Score) {
            currentPair.setWinner(currentPair.getP1());
        } else {
            currentPair.setWinner(currentPair.getP2());
        }
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    private void setWinnerDeathMetch(ActionEvent event){
        MenuItem n = (MenuItem) event.getSource();

        if(n.getText().startsWith("P1"))
            currentPair.setWinner(currentPair.getP1());
        else
            currentPair.setWinner(currentPair.getP2());

        deathMatch.setText(n.getText());

        NextOrSaveBtn.setText("Save and exit");
        NextOrSaveBtn.setDisable(false);
        NextOrSaveBtn.setOnAction(actionEvent -> saveAndExitAfterDeathMatch());
    }

    private void saveAndExitAfterDeathMatch(){
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    private void clearWindow(){
        p1ScoreRace1.setDisable(false);
        p2ScoreRace1.setDisable(false);
        p1ScoreRace2.setDisable(false);
        p2ScoreRace2.setDisable(false);
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;
        roundText.setText("Round: " + roundCount);
        NextOrSaveBtn.setDisable(true);
    }
    
}
