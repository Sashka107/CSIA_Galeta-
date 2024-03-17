package com.csia_galeta.ser;

import com.csia_galeta.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/*
 Class SceneOpener
 This utility class contains methods for opening windows
 of the program and accessing their controllers.
 */
public class SceneOpener {

    /*
     This static method opens the scene/window and returns its controller
     for further management.

     @return The controller of the scene that will be opened
     @param fxmlName The name of the window to be opened
     @param sceneTitle The title of the window to be set for the opened window
     @param sceneToClose The scene to be closed - the previous scene/window
     */
    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle, Window sceneToClose){

        // Load the layout of the scene to be opened
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));

        // Create but do not initialize the scene object
        Scene scene;

        // Attempt to initialize/load the scene by the markup file name
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) { // If unsuccessful.
            throw new RuntimeException(e);
        }

        // If successful, open the scene, set the title, close the previous scene, and return the controller.
        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        ((Stage)sceneToClose).close();
        return fxmlLoader.getController();
    }

    /*
     This static method opens the scene/window and returns its controller for further management.
     It does not close the previous scene/window.

     @return The controller of the scene that will be opened.
     @param fxmlName The name of the window to be opened.
     @param sceneTitle The title of the window to be set for the opened window.
     */
    public static <T> T openSceneAndReturnController(String fxmlName, String sceneTitle){

        // Load the layout of the scene to be opened.
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(fxmlName));

        // Create but do not initialize the scene object.
        Scene scene;

        // Try to initialize/load the scene using the FXML file name.
        try{
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) { // If unsuccessful.
            throw new RuntimeException(e);
        }

        // If successful, open the scene, set the title,
        // and return the controller without closing the previous scene
        Stage newStage = new Stage();
        newStage.setTitle(sceneTitle);
        newStage.setScene(scene);
        newStage.show();
        return fxmlLoader.getController();
    }

}