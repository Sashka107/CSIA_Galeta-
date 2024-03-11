package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Class AddDriverController
 * Отвечает за добавления водителя к соревнованию
 *
 * @author Alexander G.
 */
public class AddDriverController {

    @FXML
    private TextField driverName; // переменная-ссылка на текстовое поле для ввода имени

    @FXML
    private TextField driverSurname; // переменная-ссылка на текстовое поле для ввода фамилии

    @FXML
    private TextField driverNumber; // переменная-ссылка на текстовое поле для ввода номера

    @FXML
    private TextField driverTeam; // переменная-ссылка на текстовое поле для ввода команды

    @FXML
    private Button addDriverBtn; // переменная-ссылка на на кнопку, которая добавляет водителя


    /**
     * Функция вызывается при нажатии кнопки дабавить водителя и выполняет действие добавления
     * водителя к текущему соревнованию
     */
    @FXML
    public void addDriver(){
        Driver driver = new Driver(); // временный объект водителя для добавления
        byte nSuccess = 0; // переменная для отслеживания успешных вводов данных о водителе

        if(driver.setNameD(driverName.getText())) // пробуем установить водителю текст с поля
            nSuccess++; // если все хорошо - повышаем количество удачных вводов
        else
            showWarning("Your name is incorrect"); // вызываем функцию отбражения ошибки

        if(driver.setSurnameD(driverSurname.getText())) // аналогично как выше
            nSuccess++;
        else
            showWarning("Your surname is incorrect");

        if(driver.setNumber(driverNumber.getText())) // аналогично как выше
            nSuccess++;
        else
            showWarning("Number incorrect");

        if(driver.setTeam(driverTeam.getText())) // аналогично как выше
            nSuccess++;
        else
            showWarning("Your name is incorrect");

        if(nSuccess == 4){ // если все поля про водителя введены правильно, то:
            CompetitionSingleton.addDriverToTmpCompetition(driver); // добавляем водителя в текущее соревнование
            System.out.println("Close driver window");
            Stage stage = (Stage) addDriverBtn.getScene().getWindow();
            stage.close(); // закрываем окно добавления драйвера

            // открываем снова окно создания/редактирование соревнования
            CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml", "Create New Competition");
            controller.loadTmpCompetition();
        }
    }


    /**
     * Функция для отображения ошибки в случае если данные о водителе содержат ошибки
     * @param message - строка с предупреждением в зависимости от места где ошибка возникает
     */
    private void showWarning(String message){
        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show(); // показываем ошибку-предупреждение
    }

}
