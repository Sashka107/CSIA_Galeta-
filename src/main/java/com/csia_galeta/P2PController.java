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

public class P2PController {

    public Button saveAndExitBtn;
    public Button nextNet;
    public Label windowTitle;
    @FXML
    private ListView<Pair> pairListView = new ListView<>();

    @FXML
    public void handleClickOnPair(MouseEvent mouseEvent){
        Pair p = pairListView.getSelectionModel().getSelectedItem();

        if(p != null){
            if(p.getWinner() != null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This pair already get scores");
                alert.setHeaderText("Pair can`t be edited");
                alert.show();
                return;
            }
            editPair(p);
        }
    }

    public void load(){
        if(CompetitionSingleton.getCurrentCompetition().getCompetitionState().equals(CompetitionStates.FINAL_ROUND)){
            windowTitle.setText("Final");
            saveAndExitBtn.setDisable(true);
            nextNet.setText("Finish competition");
            nextNet.setOnAction(event -> finish());
            pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
            return;
        }

        if(CompetitionSingleton.getCurrentCompetition().getListOfPairs() == null ||
                CompetitionSingleton.getCurrentCompetition().getListOfPairs().size() == 0){
            CompetitionSingleton.getCurrentCompetition().setListOfPairs(Pair.createPairs(CompetitionSingleton.getCurrentCompetition().getListOfDrivers()));
            CompetitionSingleton.saveCurrentCompetition();
        }

        pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
    }

    public void toNextNet(){
        System.out.println("Next net activate");

        if(!checkAllWinners()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return;
        }

        if(CompetitionSingleton.getCurrentCompetition().getListOfDrivers().size() == 4){
            finalPlay();
            return;
        }

        generateNextNet();
    }

    private void finalPlay(){
        System.out.println("final play");

        Pair winners = new Pair();
        winners.setPairNum(1);
        Pair losers = new Pair();
        losers.setPairNum(2);
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winners.addDriver(pair.winner);
            if(pair.p1.equals(pair.winner)){
                losers.addDriver(pair.p2);
            }else {
                losers.addDriver(pair.p1);
            }
        }

        List<Pair> newPairs = new ArrayList<>(2);
        newPairs.add(winners);
        newPairs.add(losers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(newPairs);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFinalRound();
        CompetitionSingleton.saveCurrentCompetition();

        windowTitle.setText("Final");
        saveAndExitBtn.setDisable(true);
        nextNet.setText("Finish competition");
        nextNet.setOnAction(event -> finish());
        load();
    }

    private void generateNextNet(){
        System.out.println("Generating next net");

        List<Driver> newDrivers = new ArrayList<>();
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() != null)
                newDrivers.add(pair.getWinner());
            else
                throw new NullPointerException("Winner in pair is null for create next net drivers list");
        }

        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(newDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.saveCurrentCompetition();
        load();
    }

    private boolean checkAllWinners(){
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() == null)
                return false;
        }

        return true;
    }

    private void finish(){
        System.out.println("Finish competition");

        if(!checkAllWinners()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return;
        }

        List<Driver> winnersDrivers = new ArrayList<>(4);

        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winnersDrivers.add(pair.getWinner());
            if(pair.getP1().equals(pair.winner)){
                winnersDrivers.add(pair.getP2());
            }else {
                winnersDrivers.add(pair.getP1());
            }
        }

        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(winnersDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFullDone();
        CompetitionSingleton.saveCurrentCompetition();
        FinalResultsController controller = SceneOpener.openSceneAndReturnController("FinalAfterPair.fxml", "Final Results", pairListView.getScene().getWindow());
        controller.load();
    }
    
    private void editPair(Pair pair){
        PairEditController controller = SceneOpener.openSceneAndReturnController("PairEdit.fxml", "Pair Edit", pairListView.getScene().getWindow());
        controller.load(pair);
    }

    public void saveAndExit(ActionEvent actionEvent) {
            CompetitionSingleton.saveCurrentCompetition();
            SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }
}
