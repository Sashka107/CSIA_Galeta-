package com.csia_galeta;

import com.csia_galeta.controllers.DriverQualificationAddController;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.ChooseCompetitionTable;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * Class QualificationController
 * Этот класс является контроллером для окна квалификации,
 * описывает свойства и функционал квалификации
 *
 * @author Alexander G.
 */
public class QualificationController  {

    @FXML
    private ListView<Driver> listView; // переменная-ссылка на список водителей

    @FXML
    private Button saveAndExitBtn; // переменная-ссылка на кнопку выйти

    /**
     * метод для загрузки данных в окно UI
     */
    public void loadWindow(){
        // получаем текущее соревнование
        Competition c = CompetitionSingleton.getCurrentCompetition();

        // берем из соревнования список водителей
        List<Driver> driversFromCompetition = c.getListOfDrivers();

        // добавляем водителей по случайным индексам в UI
        for(int i = 0; i < driversFromCompetition.size(); i++){
            listView.getItems().addAll(driversFromCompetition.get(c.getQualification().getRandNums()[i]));
        }
    }

    /**
     * Метод для отслеживания клика по водителю в окне квалификации
     *
     * @param mouseEvent событие, которое срабатывает при нажатии на водителя
     */
    @FXML
    public void handleClickOnDriver(MouseEvent mouseEvent){
        System.out.println(listView.getSelectionModel().getSelectedItem().toString());

        // запускаем функцию изменения очков для выбранного водителя
        changeQScores(listView.getSelectionModel().getSelectedItem());
    }

    /**
     * Метод для изменение очков для выбранного водителя
     *
     * @param d водитель, которого будем оценивать квалификацию
     */
    @FXML
    public void changeQScores(Driver d){
        // открываем окно оценки квалификации
        DriverQualificationAddController controller = SceneOpener.openSceneAndReturnController("UserQualificationAddScore.fxml",
                "Change Driver Q Scores",
                listView.getScene().getWindow());
        controller.load(d);
    }

    /**
     * Метод для сохранения и выхода по нажатию на кнопку назад
     */
    @FXML
    private void saveAndExit(){
        // сохраняет текущее соревнование
        CompetitionSingleton.saveCurrentCompetition();

        // выходит на главный экран
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }

    /**
     * Метод, который отвечает за переход на окно парных заездов
     */
    @FXML
    private void proceedToRunInPairs(){
        // получаем список участников в квалификации
        List<Driver> drivers = CompetitionSingleton.getCurrentCompetition().getListOfDrivers();

        // проверяем у всех ли поставлена оценка за все заезды квалификации
        for(Driver d : drivers){
            // если у кого-то не поставлена
            if(d.getLastCompletedQRound() < CompetitionSingleton.getCurrentCompetition().getAmountOfQualifyingRounds()){
                // высвечиваем ошибку
                Alert warn = new Alert(Alert.AlertType.ERROR);
                warn.setContentText("Fill all qualification scores for all drivers");
                warn.show();
                return; // выходим из функции
            }
        }

        // если у всех есть все оценки,то:
        // сортируем список от большей оценки до меньшей
        getBestAndSort(drivers);

        // проводим необходимые обновления данных и сохранения
        CompetitionSingleton.getCurrentCompetition().setQualificationOfDrivers(drivers);
        CompetitionSingleton.saveCurrentCompetition();
        ChooseCompetitionTable.prepareDriversForPairRuns(drivers);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateRunInPairsInProgress();
        CompetitionSingleton.saveCurrentCompetition();

        // открываем окно парных заездов
        P2PController controller = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Run in pairs",
                listView.getScene().getWindow());
        controller.load();
    }

    /**
     * Метод для сортировки и вывода на консоль водителей по лучшим оценкам
     *
     * @param drivers список водителей для сортировки и вывода
     */
    public void getBestAndSort(List<Driver> drivers) {
        System.out.println("This is a sorted array of driver's best scores: ");
        selectionSort(drivers);
        for (Driver d : drivers) {
            System.out.println(d);
        }
    }

    /**
     * Метод, который выполяет сортировку водителей по алгоритму Selection Sort
     * По параметру лучших очков в квалификации от большего к меньшему
     *
     * @param drivers список водителей для сортировки
     */
    public void selectionSort(List<Driver> drivers) {
        int n = drivers.size();

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (drivers.get(j).getMaxQScore() > drivers.get(maxIndex).getMaxQScore()) {
                    maxIndex = j;
                }
            }

            Driver temp = drivers.get(maxIndex);
            drivers.set(maxIndex, drivers.get(i));
            drivers.set(i, temp);
        }
    }
}
