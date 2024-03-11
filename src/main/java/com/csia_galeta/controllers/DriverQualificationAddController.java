package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.QualificationController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class DriverQualificationAddController
 * Отвечает за поведение окна добавления очков за квалификацию для водителя
 *
 * @author Alexander G.
 */
public class DriverQualificationAddController {

    @FXML
    private TextField driverScore; // переменная-ссылка на поле для ввода очков

    @FXML
    private Label driverInfo; // переменная-ссылка на поле для вывода информации о водителе

    @FXML
    private MenuButton menuButton; // переменная-ссылка на кнопку для выбора раунда

    @FXML
    private Button exitBtn; // переменная-ссылка на кнопку для выхода из окна


    private Driver editDriver; // текущий водитель для редактирования
    private int roundSelected = -1; // по умолчанию выбранный раунд
    private boolean isAssessed = false; // состояние был ли оценен водитель ранее

    /**
     * Метод для загрузки редактирования очков квалификации водителя
     *
     * @param d - водитель для редактирования
     */
    public void load(Driver d){

        editDriver = d; // присваиваем параметр во внутренюю переменую
        driverInfo.setText(editDriver.toString()); // отображаем информацию о водителе

        // получаем сколько раундов квалификации для текущего соревнования
        int n = CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds();


        List<MenuItem> items = new ArrayList<>();

        // загружавем количество раундов в кнопку-меню выбора раунда
        for(int i = 1; i <= n; i++){
            MenuItem item = new MenuItem(i + "");
            items.add(item);

            // устанавливаем метод для обработки нажатия
            item.setOnAction(event -> selectRound(event));
        }

        // добавляем все кнопки раундов в кнопку-меню
        menuButton.getItems().addAll(items);
    }

    /**
     * Метод для обработки нажатия по раунду из кнопки-меню
     *
     * @param event - событие нажатия, создаваемое системой JavaFX
     */
    private void selectRound(ActionEvent event){

        // получаем из события объект по которому было совершенно нажатие
        MenuItem n = (MenuItem) event.getSource();

        // через текст раунда получаем число какой раунд выбран
        roundSelected = Integer.parseInt(n.getText());

        // проверяем был ли уже заполнен раунд и если да - отображаем информацию об очках
        if(roundSelected < editDriver.getLastCompletedQRound() + 1 && editDriver.getQualificationScore().get(roundSelected-1) != null){
            driverScore.setText(editDriver.getQualificationScore().get(roundSelected-1) + "");
            driverScore.setEditable(false);
            isAssessed = true;
        }
        // делаем проверки, чтобы раунды заполнялись один за другим
        else if(roundSelected != editDriver.getLastCompletedQRound() + 1){
            roundSelected = editDriver.getLastCompletedQRound() + 1;
            isAssessed = false;
        }

        // отображаем на кнопке какой раунд был выбран из списка меню
        menuButton.setText("Round #" + roundSelected);

        // пробуем достать раунд по индексу из водителя
        try{
            editDriver.getQualificationScore().get(roundSelected-1);
        }catch (IndexOutOfBoundsException ex){ // если нет такого - разрешаем редактировать очки для этого раунда
            driverScore.setText("");
            driverScore.setEditable(true);
        }
    }

    /**
     * Метод, который сохраняет очки квалификации за раунд
     *
     * @return true - если все прошло успешно, false - если нет
     */
    @FXML
    private boolean saveForRound(){
        // если раунд не был выбран - высвечивается окно предупреждения
        if(roundSelected == -1){
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Round not Selected");
            alertWarning.setContentText("ROUND NOT SELECTED - please select it");
            alertWarning.show();
            return false;
        }

        // если очки введенные пусты или не соответствуют ограничениям - высвечивается окно предупреждения
        if(driverScore.getText().isEmpty() || !driverScore.getText().matches("\\b(?:[0-9]|[1-9][0-9]|100)\\b")){
            Alert alertWarning = new Alert(Alert.AlertType.WARNING);
            alertWarning.setTitle("Incorrect value");
            alertWarning.setContentText("Provide value from 0 to 100");
            alertWarning.show();
            return false;
        }

        // если водитель уже был оценен
        if (!isAssessed){
            editDriver.addDriverScoreForQRound(Integer.parseInt(driverScore.getText()));
        }

        // сохраняем текущее соревнование
        CompetitionSingleton.saveCurrentCompetition();
        driverScore.setEditable(false);
        return true;
    }

    /**
     * Метод, который отвечает за выход из редактирования одного водителя к списку квалификации
     */
    @FXML
    private void exitToQList() {

        // если не получилось сохранить очки за раунд
        if(!saveForRound())
            return; // выходим из функции и не даем выйти к списку квалификации

        // если все хорошо - выходим к списку квалификации
        QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                "Qualification",
                exitBtn.getScene().getWindow());

        controller.loadWindow();
    }
}