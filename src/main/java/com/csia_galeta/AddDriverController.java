package com.csia_galeta;

import com.csia_galeta.people.Driver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDriverController {

    @FXML
    private TextField driverName;

    @FXML
    private TextField driverSurname;

    @FXML
    private TextField driverNumber;

    @FXML
    private TextField driverTeam;

    @FXML
    private Button addDriverBtn;

    @FXML
    public void addDriver(){
        Driver driver = new Driver();
        driver.setNameD(driverName.getText());
        driver.setSurnameD(driverSurname.getText());

        driver.setNumber(Byte.parseByte(driverNumber.getText()));
        driver.setTeam(driverTeam.getText());

        CompetitionSingleton.addDriverToTmpCompetition(driver);

        System.out.println("Close driver window");
        Stage stage = (Stage) addDriverBtn.getScene().getWindow();
        stage.close();
    }

}
