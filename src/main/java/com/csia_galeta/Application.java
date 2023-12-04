package com.csia_galeta;

import com.csia_galeta.ser.SceneOpener;
import javafx.stage.Stage;

public class Application extends javafx.application.Application { 
    @Override
    public void start(Stage stage) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View");
    }

    public static void main(String[] args) {
        launch();
    }
}