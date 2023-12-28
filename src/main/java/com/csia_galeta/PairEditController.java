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

        /*if(p.p2.equals(Driver.EMPTY_DRIVER)){
            withEmptyMatch();
            return;
        }*/

        d1Label.setText(p.p1.toStringPairEdit());
        d1Label2.setText(p.p1.toStringPairEdit());
        d2Label.setText(p.p2.toStringPairEdit());
        d2Label2.setText(p.p2.toStringPairEdit());
        deathMatch.setDisable(true);
        NextOrSaveBtn.setDisable(true);
    }

    public void setScoreRace1(){
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

        for(int i = 0; i < 2; i++){
            MenuItem item = new MenuItem("Player " + (i+1));
            item.setOnAction(event -> setWinnerDeathMetch(event));
            items.add(item);
        }

        deathMatch.getItems().addAll(items);
        deathMatch.setDisable(false);
        NextOrSaveBtn.setDisable(true);
    }

    /*private void withEmptyMatch(){
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
        roundText.setText("Is ready to compete");

        List<MenuItem> items = new ArrayList<>();
        MenuItem yesItem = new MenuItem("Yes");
        MenuItem noItem = new MenuItem("No");

        yesItem.setOnAction(event -> ready());
        noItem.setOnAction(event -> notReady());
        items.add(yesItem);
        items.add(noItem);

        deathMatch.getItems().addAll(items);
        deathMatch.setDisable(false);
        NextOrSaveBtn.setDisable(true);
    }

    private void ready(){
        p1Score = 100;
    }

    private void notReady(){

    }*/

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

        if(n.getText().equals("Player 1"))
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
