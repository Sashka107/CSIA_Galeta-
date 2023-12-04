package com.csia_galeta;

import com.csia_galeta.controllers.DriverQualificationAddController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class QualificationController {

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
        //
    }

    public void getBestAndSort(List<Driver> drivers) {
        int[] bestScores = new int[drivers.size()];
        int tmpScore;
        for (int i = 0; i < drivers.size(); i++) {
            for (int j = 1; j < drivers.get(i).getQualificationScore().size(); j++) {
                tmpScore = drivers.get(i).getQualificationScore().get(j - 1);
                System.out.println("tmp score = " + tmpScore);
                if (tmpScore < drivers.get(i).getQualificationScore().get(j)) {
                    tmpScore = drivers.get(i).getQualificationScore().get(j);
                }
                bestScores[i] = tmpScore;
            }
        }
        System.out.println("This is a sorted array of driver's best scores: ");
        selectionSort(bestScores);
        for (int k = 0; k < bestScores.length; k++) {
            System.out.println(bestScores[k]);
        }

    }

    public void selectionSort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] > array[maxIndex]) {
                    maxIndex = j;
                }
            }

            // Swap the found maximum element with the first element
            int temp = array[maxIndex];
            array[maxIndex] = array[i];
            array[i] = temp;
        }
    }

}
