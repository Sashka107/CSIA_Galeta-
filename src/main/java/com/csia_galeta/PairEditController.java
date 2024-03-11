package com.csia_galeta;


import com.csia_galeta.people.Pair;
import com.csia_galeta.ser.SceneOpener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Class PairEditController
 * Этот класс является контроллером окна оценивания пары в заездах.
 * Описывает логику оценивания по заездам и также DeathMatch
 *
 * @author Alexander G.
 */
public class PairEditController {

    public TextField p1ScoreRace1; // переменная-ссылка для поля оценки первого игрока в первом заезде
    public TextField p2ScoreRace1; // переменная-ссылка для поля оценки второго игрока в первом заезде
    public TextField p2ScoreRace2; // переменная-ссылка для поля оценки второго игрока во втором заезде
    public TextField p1ScoreRace2; // переменная-ссылка для поля оценки первого игрока во втором заезде
    public MenuButton deathMatch; // переменная-ссылка для кнопки-меню deathMatch
    public Label roundText; // переменная-ссылка для поля текущего заезда пары
    public Button NextOrSaveBtn; // переменная-ссылка для кнопки "Сохранить или следующий заезд"
    public Label d1Label; // переменная-ссылка для поля с информацией про первого игрока в первой гонке
    public Label d2Label; // переменная-ссылка для поля с информацией про второго игрока в первой гонке
    public Label d2Label2; // переменная-ссылка для поля с информацией про второго игрока во второй гонке
    public Label d1Label2; // переменная-ссылка для поля с информацией про первого игрока во второй гонке

    private int p1Score; // оценка первого игрока
    private int p2Score; // оценка второго игрока

    private int raceCount = 0; // количество заездов пары
    private int roundCount = 1; // количество раундов

    private Pair currentPair; // пара, которая оценивается

    /**
     * метод для загрузки информации в UI окно про пару
     *
     * @param p пара для заездов и оценки
     */
    public void load(Pair p){

        // сохраняем пару в переменную
        currentPair = p;

        // устанавливаем текст в текстовые поля и выключаем не нужные кнопки
        d1Label.setText(p.p1.toStringPairEdit());
        d1Label2.setText(p.p1.toStringPairEdit());
        d2Label.setText(p.p2.toStringPairEdit());
        d2Label2.setText(p.p2.toStringPairEdit());
        deathMatch.setDisable(true);
        NextOrSaveBtn.setDisable(true);
    }

    /**
     * Метод устанавливает очки за первую гонку
     */
    public void setScoreRace1(){

        // если очки выставлены не корректно
        if(!checkRaceScores(p1ScoreRace1) && !checkRaceScores(p2ScoreRace1)){
            // высвечиваем предупреждение
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Incorrect scores. Please provide from 0 to 10 and press enter");
            alert.show();
            return; // выходим из функции
        }

        // если поле очков первого игрока в первом заезде не пустое
        if(!p1ScoreRace1.getText().isEmpty()){
            // то преобразовываем текст из поля и устанавливаем в переменную для очков
            int p1Score = Integer.parseInt(p1ScoreRace1.getText());
            // второму игроку ставим остаток от очков, расчитывается так: 10 - очки первого игрока
            p2ScoreRace1.setText(10 - p1Score + "");

            // плюсуем общие очки за заезд для обоих участников
            this.p1Score += p1Score;
            this.p2Score += 10 - p1Score;
        }else if(!p2ScoreRace1.getText().isEmpty()){ // делаем то же самое только наоборот участники
            int p2Score = Integer.parseInt(p2ScoreRace1.getText());
            p1ScoreRace1.setText(10 - p2Score + "");
            this.p2Score += p2Score;
            this.p1Score += 10 - p2Score;
        }

        // выключаем не нужные кнопки
        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        raceCount++; // увиличиваем количество заездов
        NextOrSaveBtn.setDisable(true);

        // проверяем не окончен ли раунд
        checkForRound();
    }

    /**
     * Метод устанавливает очки за вторую гонку
     * Метод полностью аналогичен методу выше, только делает для второго заезда
     */
    public void setScoreRace2(){
        if(!checkRaceScores(p1ScoreRace2) && !checkRaceScores(p2ScoreRace2)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Incorrect scores. Please provide from 0 to 10 and press enter");
            alert.show();
            return;
        }

        if(!p1ScoreRace2.getText().isEmpty()){
            int p1Score = Integer.parseInt(p1ScoreRace2.getText());
            p2ScoreRace2.setText(10 - p1Score + "");
            this.p1Score += p1Score;
            this.p2Score += 10 - p1Score;
        }else if(!p2ScoreRace2.getText().isEmpty()){
            int p2Score = Integer.parseInt(p2ScoreRace2.getText());
            p1ScoreRace2.setText(10 - p2Score + "");
            this.p2Score += p2Score;
            this.p1Score += 10 - p2Score;
        }

        p1ScoreRace2.setDisable(true);
        p2ScoreRace2.setDisable(true);
        raceCount++;
        NextOrSaveBtn.setDisable(true);
        checkForRound();
    }

    /**
     * Метод для проверки правильности ввода очков для заезд для участника
     *
     * @return true - если очки корректны, false - если нет
     */
    private boolean checkRaceScores(TextField field){

        // проверяем пусто поле и содержит ли оно не только цифры
        if(field.getText().isEmpty() || !field.getText().matches("[0-9]+"))
            return false; // если да - false

        // если условия выше соблюдены - преобразовываем число
        int score = Integer.parseInt(field.getText());
        return score >= 0 && score <= 10; // и проверяем находится ли число в диапазоне от 0 до 10
    }

    /**
     * Метод для проверки сколько раундов уже сыграно парой
     */
    public void checkForRound(){

        // елси раундов меньше или 2, то выходим из функции
        if (raceCount != 2)
            return;

        System.out.println("P1 score = " + p1Score);
        System.out.println("P2 score = " + p2Score);

        // если раундов 3 и очков по равну у обоих
        if(roundCount == 3 && p1Score == p2Score){
            // то запускаем Death-Match
            NextOrSaveBtn.setText("Death-Match");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> deathMatch());
        } else if (p1Score == p2Score){ // если в раунде одинаково очков
            // то продолжаем заезды как OMT
            NextOrSaveBtn.setText("OMT");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> clearWindow());
        } else {
            // в инном случае сохраняем результаты, то есть это означает
            // что у кого-то больше очков а у кого-то меньше
            NextOrSaveBtn.setText("Save pair results");
            NextOrSaveBtn.setDisable(false);
            NextOrSaveBtn.setOnAction(actionEvent -> saveAndExit());
        }
    }

    /**
     * Метод для запуска death-match
     */
    private void deathMatch(){

        // выключаем ненужные кнопки
        p1ScoreRace1.setDisable(true);
        p2ScoreRace1.setDisable(true);
        p1ScoreRace2.setDisable(true);
        p2ScoreRace2.setDisable(true);

        // убераем все очки из полей
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");

        // обнуляем все переменные
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;

        // устанавливаем заголовок
        roundText.setText("Death-match");

        List<MenuItem> items = new ArrayList<>();

        // формируем выбор кто выиграл в кнопку-меню
        for(int i = 0; i < 2; i++){
            MenuItem item = new MenuItem("Player " + (i+1));
            item.setOnAction(event -> setWinnerDeathMatch(event));
            items.add(item);
        }

        // добавляем пункты меню, кто выиграл
        deathMatch.getItems().addAll(items);

        // включаем и выключаем кнопки
        deathMatch.setDisable(false);
        NextOrSaveBtn.setDisable(true);
    }


    /**
     * Метод для сохранения и выхода из окна оценки участников пары
     */
    private void saveAndExit(){

        // проверяем кто выиграл - того и ставим победителем пары
        if(p1Score > p2Score) {
            currentPair.setWinner(currentPair.getP1());
        } else {
            currentPair.setWinner(currentPair.getP2());
        }

        // возвращаемся на окно всех пар
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    /**
     * Метод для установки кто выиграл в death-match
     *
     * @param event событие при клике на кнопку
     */
    private void setWinnerDeathMatch(ActionEvent event){

        // получаем пункт из кнопки-меню из события нажатия
        MenuItem n = (MenuItem) event.getSource();

        // проверяем кого было выбрано и ставим победителем
        if(n.getText().equals("Player 1"))
            currentPair.setWinner(currentPair.getP1());
        else
            currentPair.setWinner(currentPair.getP2());

        deathMatch.setText(n.getText());

        // ставим текст в кнопку и разрешаем нажатие
        NextOrSaveBtn.setText("Save and exit");
        NextOrSaveBtn.setDisable(false);
        NextOrSaveBtn.setOnAction(actionEvent -> saveAndExitAfterDeathMatch());
    }

    /**
     * Метод для сохранения и выхода после death-match
     */
    private void saveAndExitAfterDeathMatch(){
        // далаем возврат к окну с парами
        P2PController controller2 = SceneOpener.openSceneAndReturnController("PairToPairView.fxml",
                "Runs in pairs",
                p1ScoreRace1.getScene().getWindow());
        controller2.load();
    }

    /**
     * Метод для отчистки окна оценки пары, используется между заездами пары OMT
     */
    private void clearWindow(){
        // включаем нужные поля для ввода очков
        p1ScoreRace1.setDisable(false);
        p2ScoreRace1.setDisable(false);
        p1ScoreRace2.setDisable(false);
        p2ScoreRace2.setDisable(false);

        // убераем текст из полей для очков
        p1ScoreRace1.setText("");
        p2ScoreRace1.setText("");
        p1ScoreRace2.setText("");
        p2ScoreRace2.setText("");

        // обнуляем переменные
        p1Score = 0;
        p2Score = 0;
        raceCount = 0;
        roundCount++;

        // меняем текст и выключаем кнопку
        roundText.setText("Round: " + roundCount);
        NextOrSaveBtn.setDisable(true);
    }
    
}