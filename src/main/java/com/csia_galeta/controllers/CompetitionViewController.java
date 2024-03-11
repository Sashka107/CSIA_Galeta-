package com.csia_galeta.controllers;

import com.csia_galeta.*;
import com.csia_galeta.people.Driver;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 * Class CompetitionViewController
 * Отвечает за поведение окна отображения уже созданного ранее соревнования
 *
 * @author Alexander G.
 */
public class CompetitionViewController {

    @FXML
    private Label competitionName; // Переменная-ссылка на поле для отображения текста названия
    @FXML
    private Label dateText; // Переменная-ссылка на поле для отображения текста даты
    @FXML
    private Label judgesNames; // Переменная-ссылка на поле для отображения текста имен судий
    @FXML
    private Label plannedStatus; // Переменная-ссылка на поле для отображения текста статуса
    @FXML
    private Label qRounds; // Переменная-ссылка на поле для отображения текста количества раундов

    @FXML
    private ListView<Driver> listView; // Переменная-ссылка на список для отображения участников

    @FXML
    private Button playBtn; // Переменная-ссылка на кнопку по которой можно начать соревнование

    @FXML
    private Button backBtn; // Переменная-ссылка на кнопку по которой можно выйти назад к списку созданных соревнований

    /**
     * Метод нужен для того, чтобы загружать актуальную информацию о выбранном соревновании
     * во все перечисленные выше переменные-ссылки UI
     *
     * @param competition - соревнование выбранное из списка уже созданных
     */
    public void load(Competition competition){
        CompetitionSingleton.setCurrentCompetition(competition); // устанавливаем в текущее соревнование выбранное
        competitionName.setText(competition.getCompetitionName());
        qRounds.setText(competition.getAmountOfQualifyingRounds() + "");
        dateText.setText(competition.getCompetitionDate());
        judgesNames.setText(competition.getListOfJudgesString());
        plannedStatus.setText(competition.getCompetitionState());
        listView.getItems().addAll(competition.getListOfDrivers());
    }

    /**
     * Метод, который отвечает за выход назад к окну выбора соревнований из списка уже созданных,
     * срабатывает по назатии на кнопку назад
     */
    @FXML
    private void back(){
        // получаем контроллер предыдущей сцены и закрываем текущую
        PrevCompetitionsController controller =
                SceneOpener.openSceneAndReturnController("PrevCompetitions.fxml",
                "All Saved Competitions",
                backBtn.getScene().getWindow());

        // открываем сцену
        controller.showCompetitions(CompetitionSingleton.getInstance().getCompetitions());
    }


    /**
     * Метод срабатывает при нажатии на кнопку "начать соревнование" и открывает его в зависимости
     * от текущего статуса завершенности
     */
    @FXML
    private void playCompetition(){
        // в зависимости от статуса открываем разные сцены
        switch (CompetitionSingleton.getCurrentCompetition().getCompetitionState()) {
            case CompetitionStates.QUALIFICATION_IN_PROGRESS -> { // открываем квалификацию
                QualificationController controller = SceneOpener.openSceneAndReturnController("QualificationView.fxml",
                        "Qualification",
                        playBtn.getScene().getWindow());
                controller.loadWindow();
            }
            case CompetitionStates.PLANNED -> { // открываем редактирование
                CreateEditCompetitionController controller = SceneOpener.openSceneAndReturnController(
                        "CreateOrEditCompetition.fxml",
                        "Edit Competition",
                        playBtn.getScene().getWindow());
                controller.loadToEditCompetition();
            }
            case CompetitionStates.RUN_IN_PAIRS_IN_PROGRESS, CompetitionStates.FINAL_ROUND ->
            { // открываем парные заезды
                    System.out.println("RUN IN PAIRS OPEN");

                    P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                        "Runs in pairs",
                        playBtn.getScene().getWindow());
                    controller2.load();
            }
            case CompetitionStates.COMPETITION_IS_FINISHED -> { // открываем финальное окно результатов
                FinalResultsController controller = SceneOpener.openSceneAndReturnController("FinalAfterPair.fxml", "Final Results"
                        , backBtn.getScene().getWindow());
                controller.load();
            }
            default -> { // на всякий случай обрабатываем случай если что-то пошло не правильно в статусах
                Alert alertWarning = new Alert(Alert.AlertType.WARNING);
                alertWarning.setTitle("Can`t Play!");
                alertWarning.setContentText("You can`t play:\nCompetition already FINISHED");
                alertWarning.show();
            }
        }
    }

}