package com.csia_galeta;

import com.csia_galeta.controllers.DriverQualificationAddController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

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

    }

}
