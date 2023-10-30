package com.csia_galeta;

import com.csia_galeta.people.Judge;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddJudgeController
{

    @FXML
    private TextField judgeName;

    @FXML
    private TextField judgeSurname;

    @FXML
    private Button addJudge;


    @FXML
    public void addJudge(){
        Judge judge = new Judge();
        judge.setName(judgeName.getText());
        judge.setSurname(judgeSurname.getText());

        CompetitionSingleton.addJudgeToTmpCompetition(judge);

        System.out.println("Close judge window");
        Stage stage = (Stage) addJudge.getScene().getWindow();
        stage.close();
    }

}
