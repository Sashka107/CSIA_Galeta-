package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Class FinalResultsController
 * Этот класс является контроллером окна финальных результатов соревнования,
 * описывает и содержит функционал для отображения результатов соревнования
 *
 * @author Alexander G.
 */
public class FinalResultsController {
    public Label top1; // переменная-ссылка на поле для отображения первого места
    public Label top2; // переменная-ссылка на поле для отображения второго места
    public Label top3; // переменная-ссылка на поле для отображения третьего места
    public Label top4; // переменная-ссылка на поле для отображения четвертого места

    public ListView<String> qDriversList; // переменная-ссылка на список для отображения квалификации

    /**
     * Метод для подгрузки информации во все поля на UI
     */
    public void load(){
        // получаем список победителей, то есть последние четыре водителя в порядке мест победы
        List<Driver> winners =  CompetitionSingleton.getCurrentCompetition().getListOfDrivers();

        // устанавливаем значения в поля на UI
        top1.setText(winners.get(0).toStringWinner());
        top2.setText(winners.get(1).toStringWinner());
        top3.setText(winners.get(2).toStringWinner());
        top4.setText(winners.get(3).toStringWinner());

        // подгружаем список водителей из квалификации с из лучшим результатом
        for(var driver : CompetitionSingleton.getCurrentCompetition().getQualificationOfDrivers()){
            qDriversList.getItems().add(driver.toStringSimple() + " best q score: " + driver.getMaxQScore());
        }
    }

    /**
     * Метод для выхода на главное меню, срабатывает при нажатии на кнопку "Выйти"
     *
     * @param actionEvent событие нажатия на кнопку
     */
    public void toMainMenu(ActionEvent actionEvent) {
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", top1.getScene().getWindow());
    }
}