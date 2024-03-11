package com.csia_galeta.controllers;

import com.csia_galeta.CompetitionSingleton;
import com.csia_galeta.PrevCompetitionsController;
import com.csia_galeta.ser.SceneOpener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class RC_DriftController
 * Отвечает за поведение главного окна программы
 *
 * @author Alexander G.
 */
public class RC_DriftController {
    public Button prevCompetitionBtn; // переменная-ссылка на кнопку прошлых/созданных ранее соревнований

    @FXML
    private Button addCompetitionBtn; // переменная-ссылка на кнопку, которая добавляет соревнование

    /**
     * Метод, который срабатывает при нажатии на кнопку "Предыдущие соревнования"
     * Открывает окно со списком всех созданных ранее и предыдущих соревнований
     */
    @FXML
    public void openPrevCompetitions(){
        PrevCompetitionsController controller = SceneOpener.openSceneAndReturnController("PrevCompetitions.fxml",
                "All Saved Competitions",
                prevCompetitionBtn.getScene().getWindow());
        controller.showCompetitions(CompetitionSingleton.getInstance().getCompetitions());
    }

    /**
     * Метод, который срабатывает при нажатии на кнопку "Добавить новое соревнование"
     * Открывает окно для создания нового соревнования
     */
    @FXML
    protected void openAddCompetitionWindow(){
        SceneOpener.openSceneAndReturnController("CreateOrEditCompetition.fxml",
                "Create New Competition",
                addCompetitionBtn.getScene().getWindow());
    }
}