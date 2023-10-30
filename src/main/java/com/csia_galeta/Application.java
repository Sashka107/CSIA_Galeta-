package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.DataSaverAndReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("RC_Drift.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Driver d1 = new Driver();
        d1.setNameD("Oleksandr");
        d1.setNumber((byte) 3);
        d1.setTeam("DIZRC777");

        Judge j1 = new Judge();
        j1.setName("Sergey");
        j1.setSurname("SKR");

        Competition c = new Competition();
        c.setCompetitionName("Test 3");
        c.setAmountOfQualifyingRounds((byte)1);
        c.addDriverToList(d1);
        c.addJudgeToList(j1);

        DataSaverAndReader.saveJsonStringToFile("save.json", c);

        launch();
    }
}