package com.csia_galeta;

import com.csia_galeta.ser.Pair;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

public class PairEditController {

    public TextField p1ScoreRace1;
    public TextField p2ScoreRace1;
    public TextField p2ScoreRace2;
    public TextField p1ScoreRace2;
    public MenuButton deathMatch;
    public Button deathMatchWonButton;
    public Label roundText;
    public Button NextOrSaveBtn;
    public Label d1Label;
    public Label d2Label;
    public Label d2Label2;
    public Label d1Label2;

    private int p1Score;
    private int p2Score;

    public void load(Pair p){
        d1Label.setText(p.p1.toStringPairEdit());
        d1Label2.setText(p.p1.toStringPairEdit());
        d2Label.setText(p.p2.toStringPairEdit());
        d2Label2.setText(p.p2.toStringPairEdit());

        deathMatchWonButton.setDisable(true);
        deathMatch.setDisable(true);


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
    }

    public void checkForRound(){
        if (!p1ScoreRace1.getText().isEmpty() && !p2ScoreRace1.getText().isEmpty() && !p1ScoreRace2.getText().isEmpty() && !p2ScoreRace2.getText().isEmpty()){
            if (p1Score == p2Score){
                NextOrSaveBtn.setText("OMT");
            } else {
                NextOrSaveBtn.setText("Save pair results");
            }
        }
    }
    
}
