package com.csia_galeta;

import com.csia_galeta.people.Driver;
import com.csia_galeta.people.Pair;
import com.csia_galeta.ser.CompetitionStates;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class P2PController
 * Этот класс является контроллером окна парных заездов и содержит в себе логику и функционал
 * переключения между сетками турнира и самим процессом парных заездов до самого завершения соревнования
 *
 * @author Alexander G.
 */
public class P2PController {

    public Button saveAndExitBtn; // переменная-ссылка на кнопку выхода
    public Button nextNet; // переменная-ссылка на кнопку к следующему этапу
    public Label windowTitle; // переменная-ссылка на поле отображающее заголовок окна
    @FXML
    private ListView<Pair> pairListView = new ListView<>(); // переменная-ссылка на список пар

    /**
     * Метод, который срабатывает при нажатии на пару из списка
     * Открывает пару для оценивания и т.д.
     *
     * @param mouseEvent событие клика по паре
     */
    @FXML
    public void handleClickOnPair(MouseEvent mouseEvent){

        // получаем объект пары по которой было нажатие
        Pair p = pairListView.getSelectionModel().getSelectedItem();

        // если пара не пуста
        if(p != null){
            if(p.getWinner() != null){ // если есть победитель
                // отображаем предупреждение и выходим из метода
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This pair already get scores");
                alert.setHeaderText("Pair can`t be edited");
                alert.show();
                return;
            }

            // если победителя ещё нет - открываем оценивание пары
            editPair(p);
        }
    }

    /**
     * Метод для загрузки информации для парных заездов в окно UI
     */
    public void load(){
        // если соревнование же на финальном раунде
        if(CompetitionSingleton.getCurrentCompetition().getCompetitionState()
                .equals(CompetitionStates.FINAL_ROUND)){

            // то устанавливаем все необходимые поля UI нужный текст
            windowTitle.setText("Final");
            saveAndExitBtn.setDisable(true); // выключаем кнопку выхода, запрещаем выход
            nextNet.setText("Finish competition");

            // устанавливаем на кнопку "К следующему этапу" выполнение другой функции завершения
            nextNet.setOnAction(event -> finish());

            // загружаем и ставим в UI список всех пар
            pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
            return;
        }

        // если список пар ещё не был создан или был пуст
        if(CompetitionSingleton.getCurrentCompetition().getListOfPairs() == null ||
                CompetitionSingleton.getCurrentCompetition().getListOfPairs().isEmpty()){

            // то создаем список пар
            CompetitionSingleton.getCurrentCompetition().setListOfPairs(Pair.createPairs(CompetitionSingleton.getCurrentCompetition().getListOfDrivers()));

            // и сохраняем его в файл JSON вместе с соревнованием
            CompetitionSingleton.saveCurrentCompetition();
        }

        // загружаем и ставим в UI список всех пар
        pairListView.getItems().setAll(CompetitionSingleton.getCurrentCompetition().getListOfPairs());
    }

    /**
     * Метод для перехода к следующему этапу парных заездов
     */
    public void toNextNet(){
        System.out.println("Next net activate");

        // проверка все-ли пары не были оценены
        if(!checkAllWinners()){
            // если не оценены - высвечиваем предупреждение
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return; // выходим из функции
        }

        // если участников осталось 4 - запускаем последнюю игру
        if(CompetitionSingleton.getCurrentCompetition().getListOfDrivers().size() == 4){
            finalPlay();
            return; // выходим из функции
        }

        // создаем следующую сетку заездов пар
        generateNextNet();
    }

    /**
     * Метод для финальных заездов пар в соревновании
     */
    private void finalPlay(){
        System.out.println("final play");

        // формируем пары для финала и заезда за 3-е место
        Pair winners = new Pair();
        winners.setPairNum(1);
        Pair losers = new Pair();
        losers.setPairNum(2);

        // победители в пару за финал и проигравших в пару за 3-е место из пар полуфинала
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winners.addDriver(pair.winner);
            if(pair.p1.equals(pair.winner)){
                losers.addDriver(pair.p2);
            }else {
                losers.addDriver(pair.p1);
            }
        }

        // создаем новый список пар для финала
        List<Pair> newPairs = new ArrayList<>(2);
        newPairs.add(winners);
        newPairs.add(losers);

        // заменяем прошлый список пар финальным списком, сохраняем все
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(newPairs);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFinalRound();
        CompetitionSingleton.saveCurrentCompetition();

        // устанавливаем все необходимые поля UI нужный текст
        windowTitle.setText("Final");
        saveAndExitBtn.setDisable(true);
        nextNet.setText("Finish competition");

        // устанавливаем на кнопку "К следующему этапу" выполнение другой функции завершения
        nextNet.setOnAction(event -> finish());

        // подгружаем в окно заново информацию о парах, теперь уже финал
        load();
    }

    /**
     * Метод для генерации следующей сетки парных заездов
     */
    private void generateNextNet(){
        System.out.println("Generating next net");

        List<Driver> newDrivers = new ArrayList<>();

        // формируем пары из победителей из прошлой сетки
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() != null)
                newDrivers.add(pair.getWinner());
            else
                throw new NullPointerException("Winner in pair is null to create next net drivers list");
        }

        // делаем необходимые сохранения и обновления в списке участников
        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(newDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.saveCurrentCompetition();

        // подгружаем в окно заново информацию о парах
        load();
    }

    /**
     * Метод нужен для проверки, что все пары были оценены
     *
     * @return true - если все были оценены, false - если хоть одна пара не оценена
     */
    private boolean checkAllWinners(){

        // перебираем все пары и смотрим победителя
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            if(pair.getWinner() == null) // если нет победителя
                return false; // возвращаем false
        }

        return true;
    }

    /**
     * Метод, который срабатывает при нажатии на кнопку "finish"
     * Формирует список победителей и открывает окно результатов
     */
    private void finish(){
        System.out.println("Finish competition");

        // проверяем, что все пары не были оценены
        if(!checkAllWinners()){
            // если не оценены все, то предупреждаем об этом
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Not all pairs has winners");
            alert.show();
            return; // выходим из функции
        }

        // если все оценены выше, создаем финальный список победителей для 4х участников
        List<Driver> winnersDrivers = new ArrayList<>(4);

        // перебираем список пар и формируем победителей
        for(var pair : CompetitionSingleton.getCurrentCompetition().getListOfPairs()){
            winnersDrivers.add(pair.getWinner());
            if(pair.getP1().equals(pair.winner)){
                winnersDrivers.add(pair.getP2());
            }else {
                winnersDrivers.add(pair.getP1());
            }
        }

        // делаем сохранение и обновление в соревновании
        CompetitionSingleton.getCurrentCompetition().setListOfDrivers(winnersDrivers);
        CompetitionSingleton.getCurrentCompetition().setListOfPairs(null);
        CompetitionSingleton.getCurrentCompetition().setCompetitionStateFullDone();
        CompetitionSingleton.saveCurrentCompetition();

        // открываем окно результатов
        FinalResultsController controller = SceneOpener.openSceneAndReturnController("FinalAfterPair.fxml", "Final Results", pairListView.getScene().getWindow());
        controller.load();
    }

    /**
     * Метод для редактирования\оценки пары
     *
     * @param pair пара для редактирования
     */
    private void editPair(Pair pair){

        // открываем окно редактирования пары
        PairEditController controller = SceneOpener.openSceneAndReturnController("PairEdit.fxml", "Pair Edit", pairListView.getScene().getWindow());
        controller.load(pair);
    }

    /**
     * Метод для сохранения и выхода из окна парных заездов
     *
     * @param actionEvent событие при нажатии на кнопку
     */
    public void saveAndExit(ActionEvent actionEvent) {
        // сохраняем текущее соревнование
        CompetitionSingleton.saveCurrentCompetition();

        // открываем главное меню
        SceneOpener.openSceneAndReturnController("RC_Drift.fxml", "Main View", saveAndExitBtn.getScene().getWindow());
    }
}
