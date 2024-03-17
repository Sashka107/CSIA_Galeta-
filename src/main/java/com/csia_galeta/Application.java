package com.csia_galeta;

import com.csia_galeta.ser.SceneOpener;
import javafx.stage.Stage;

/*
 Class Application
 This class contains methods for opening and running the program,
 using the JavaFX library for program development.
 */
public class Application extends javafx.application.Application {

    /*
     Overriding the start method from the base class of the JavaFX library,
     which sets the initial scene/window of the application.

     @param stage - the stage of the application.
     */
    @Override
    public void start(Stage stage) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml",
                "Main View");
    }

    // Main function to launch the program in Java.
    public static void main(String[] args) {
        launch(); // Invoking a function from the parent class of the JavaFX library.
    }
}