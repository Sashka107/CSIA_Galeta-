package com.csia_galeta;

import com.csia_galeta.controllers.CompetitionViewController;
import com.csia_galeta.ser.Pair;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class P2PController {

    public Button saveAndExitBtn;
    @FXML
    private ListView<Pair> pairListView = new ListView<>();

    private List<Pair> pairs;

    @FXML
    public void handleClickOnPair(MouseEvent mouseEvent){
        System.out.println(pairListView.getSelectionModel().getSelectedItem().toString());
        Pair p = pairListView.getSelectionModel().getSelectedItem();

        if(p != null)
            editPair(p);
    }

    public void load(){
        pairs =  Pair.createPairs(CompetitionSingleton.getCurrentCompetition().getListOfDrivers());
        pairListView.getItems().setAll(pairs);
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
