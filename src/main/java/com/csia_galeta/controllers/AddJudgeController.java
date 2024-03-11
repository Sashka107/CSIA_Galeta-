package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.people.Judge;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Class AddJudge Controller
 * Отвечает за добавления судьи к соревнованию
 *
 * @author Alexander G.
 */
public class AddJudgeController
{

    @FXML
    private TextField judgeName; // переменная-ссылка на текстовое поле для ввода имени

    @FXML
    private TextField judgeSurname; // переменная-ссылка на текстовое поле для ввода фамилии


    @FXML
    private Button addJudge; // переменная-ссылка на на кнопку, которая добавляет судью

    /**
     * Функция вызывается при нажатии кнопки дабавить судью и выполняет действие добавления
     * водителя к текущему соревнованию
     */
    @FXML
    public void addJudge(){
        Judge judge = new Judge(); // временный объект судьи для добавления
        byte nSuccess = 0; // переменная для отслеживания успешных вводов данных о водителе

        if(judge.setName(judgeName.getText())) // пробуем установить судье текст с поля
            nSuccess++; // если все хорошо - повышаем количество удачных вводов
        else
            showWarning("Your name is incorrect"); // вызываем функцию отбражения ошибки

        if(judge.setSurname(judgeSurname.getText())) // аналогично как выше
            nSuccess++;
        else
            showWarning("Your surname is incorrect");

        if(nSuccess == 2){ // если все поля про судью введены правильно, то:
            CompetitionSingleton.addJudgeToTmpCompetition(judge); // добавляем водителя в текущее соревнование
            System.out.println("Close judge window");
            Stage stage = (Stage) addJudge.getScene().getWindow();
            stage.close(); // закрываем окно добавления драйвера

            // открываем снова окно создания/редактирование соревнования
            CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml", "Create New Competition");
            controller.loadTmpCompetition();
        }
    }

    /**
     * Функция для отображения ошибки в случае если данные о судьи содержат ошибки
     * @param message - строка с предупреждением в зависимости от места где ошибка возникает
     */
    private void showWarning(String message){
        Alert warningsAlert = new Alert(Alert.AlertType.WARNING);
        warningsAlert.setHeaderText("Incorrect data");
        warningsAlert.setContentText(message);
        warningsAlert.show(); // показываем ошибку-предупреждение
    }

}