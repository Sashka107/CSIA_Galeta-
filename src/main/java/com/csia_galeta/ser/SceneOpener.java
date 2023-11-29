package com.csia_galeta.ser;

import com.csia_galeta.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class SceneOpener {

    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle, Window sceneToClose){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));
        Scene scene;
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        ((Stage)sceneToClose).close();
        return fxmlLoader.getController();
    }

    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle){
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));
        Scene scene;
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        return fxmlLoader.getController();
    }

}