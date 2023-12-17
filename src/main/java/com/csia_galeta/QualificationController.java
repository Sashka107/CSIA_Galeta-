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

public class QualificationController  {

    @FXML
    private ListView<Driver> listView;

    @FXML
    private Button saveAndExitBtn;

    @FXML
    private Button proceedToPairsBtn;

    public void loadWindow(){
        Competition c = CompetitionSingleton.getCurrentCompetition();
        List<Driver> driversFromCompetition = c.getListOfDrivers();
        for(int i = 0; i < driversFromCompetition.size(); i++){
            listView.getItems().addAll(driversFromCompetition.get(
                    c.getQualification().getRandNums()[i]
            ));
        }
        //listView.getItems().addAll(drivers);
    }

    @FXML
    public void handleClickOnDriver(MouseEvent mouseEvent){
        System.out.println(listView.getSelectionModel().getSelectedItem().toString());
        changeQScores(listView.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void changeQScores(Driver d){
        DriverQualificationAddController controller = SceneOpener.openSceneAndReturnController("UserQualificationAddScore.fxml",
                "Change Driver Q Scores",
                listView.getScene().getWindow());
        controller.load(d);
    }

    @FXML
    private void saveAndExit(){
        CompetitionSingleton.saveCurrentCompetition();
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }

    @FXML
    private void proceedToRunInPairs(){
        List<Driver> drivers = CompetitionSingleton.getCurrentCompetition().getListOfDrivers();
        for(Driver d : drivers){
            if(d.getLastCompletedQRound() < CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds()){
                Alert warn = new Alert(Alert.AlertType.ERROR);
                warn.setContentText("Fill all qualification scores for all drivers");
                warn.show();
                return;
            }
        }

        getBestAndSort(drivers);
        ChooseCompetitionTable.prepareDriversForPairRuns(drivers);
        CompetitionSingleton.saveCurrentCompetition();

        P2PController controller = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Run in pairs",
                listView.getScene().getWindow());
        controller.load();
    }

    public void getBestAndSort(List<Driver> drivers) {
        System.out.println("This is a sorted array of driver's best scores: ");
        selectionSort(drivers);
        for (Driver d : drivers) {
            System.out.println(d);
        }
    }
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
