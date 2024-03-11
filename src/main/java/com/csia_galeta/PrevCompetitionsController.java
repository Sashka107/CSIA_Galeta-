package com.csia_galeta;

import com.csia_galeta.controllers.CompetitionViewController;

import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

/**
 * Class PrevCompetitionsController
 * Этот класс является контроллером окна предыдущих соревнований
 *
 * @author Alexander G.
 */
public class PrevCompetitionsController {

    @FXML
    public ListView<Competition> savedCompetitions = new ListView<>(); // переменная-ссылка на список соревнований

    @FXML
    private Button backBtn; // переменная-ссылка на кнопку Назад

    /**
     * метод для подгрузки соревнований в список на UI
     *
     * @param data список соревнований для загрузки на UI
     */
    public void showCompetitions(List<Competition> data) {

        // ставим список на UI
        savedCompetitions.getItems().addAll(data);

        // ставим действие по клику на совернование в списке
        savedCompetitions.setOnMouseClicked(mouseEvent -> {

            // при клике открываем окно с предпросмотром информации о соревновании
            CompetitionViewController controller = SceneOpener.openSceneAndReturnController("PrevCompetitionView.fxml",
                    "Saved Competition View",
                    savedCompetitions.getScene().getWindow());
            controller.load(savedCompetitions.getSelectionModel().getSelectedItem());
        });
    }

    /**
     * Метод для выхода назад в главное меню по нажатию на кнопку
     */
    @FXML
    private void backToMainMenu(){
        // открываем глвное окно
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", backBtn.getScene().getWindow());
    }

}